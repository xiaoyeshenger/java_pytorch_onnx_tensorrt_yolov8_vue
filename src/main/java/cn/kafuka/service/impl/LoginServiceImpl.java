package cn.kafuka.service.impl;

import com.alibaba.fastjson.JSONObject;
import cn.kafuka.bo.po.*;
import cn.kafuka.bo.vo.UserVo;
import cn.kafuka.constant.Constant;
import cn.kafuka.jobManager.AsyncJobManager;
import cn.kafuka.jobManager.factory.AsyncFactory;
import cn.kafuka.mapper.*;
import cn.kafuka.messagePush.SmsClient;
import cn.kafuka.redis.RedisKey;
import cn.kafuka.redis.RedisService;
import cn.kafuka.util.*;
import cn.kafuka.bo.dto.UserLoginReqDto;
import cn.kafuka.bo.dto.UserRegisterReqDto;
import cn.kafuka.bo.vo.PermsVo;
import cn.kafuka.service.LoginService;
import cn.kafuka.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.update;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

/**
 * @Author 张勇
 * @Description //LoginService接口实现类
 * @Date 2021/05/21 18:43
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    //jpush短信模板id
    @Value("${sys.app.smsTplId:155844}")
    private String smsTplId;

    //验证码超时时间(5分钟)
    @Value("${sys.smsCode.timeout:300}")
    private long smsCodeTimeOut;

    //验证码重复发送时间(1分钟内只能发一次)
    @Value("${sys.sendSMSIntervalTime:60}")
    private long  sendSMSIntervalTime;

    //token失效时间(7天)
    @Value("${jwt.expiration}")
    private Long expiration;

    //登录密码加盐
    @Value("${sys.pwd.salt}")
    private String pwdSalt;

    //是否允许不验证手机验证码
    @Value("${sys.app.allowNotVerifySmsCode:false}")
    private boolean allowNotVerifySmsCode;

    //是否允许测试手机号
    @Value("${sys.app.enableTestMobile:true}")
    private boolean enableTestMobile;

    //测试手机号正则匹配
    @Value("${sys.app.testMobilePattern:11112222[0-9][0-9][0-9]}")
    private Pattern testMobilePattern;


    private final MinioUtil minioUtil;


    private final SmsClient smsClient;

    private final RedisService redisService;

    private final PermissionService permissionService;

    private final UserMapper userMapper;

    private final UserRoleMapper userRoleMapper;

    private final RoleMapper roleMapper;


    @Override
    public String getSmsCode(String mobileNumber) {

        //1.手机号码校验
        //--1.手机号不能为空
        if (StringUtils.isEmpty(mobileNumber)) {
            throw new IllegalArgumentException("手机号不能为空");
        }

        //--2.测试手机号不需要验证码
        if (enableTestMobile && testMobilePattern.matcher(mobileNumber).matches()) {
            throw new IllegalArgumentException("手机号不正确");
        }

        //2.60秒内只能发送一次
        Long expireTime = redisService.getExpireTime(RedisKey.USER_MOBILE_SMSCODE+mobileNumber);
        if(expireTime != -1){
            //距离发送短信是否超过60秒(60秒内只能发送一次短信)
            long passTime = smsCodeTimeOut - expireTime;
            //log.info("redis验证码超时时间:{},还剩余超时时间--->:{},已过去时间--->:{}",smsCodeTimeOut,expireTime,passTime);
            long canSendTime = sendSMSIntervalTime - passTime;
            if (passTime < sendSMSIntervalTime) {
                throw new IllegalArgumentException("短信已发送,请在"+canSendTime+"秒后再试");
            }
        }

        //3.调用极光平台发送短信
        Map<String, Object> tplPara = new HashMap<>(1);
        String code = generateCode(4);
        tplPara.put("pwd", code);
        JSONObject jsonObject = smsClient.sendSmg(mobileNumber, smsTplId, tplPara);

        if (ObjUtil.isEmpty(jsonObject)) {
            throw new IllegalArgumentException("获取短信验证码失败(01)");
        }
        if (!jsonObject.containsKey("msg_id")) {
            throw new IllegalArgumentException("获取短信验证码失败(02)");
        }
        log.info("验证码发送成功--> mobile: {}, code: {}", mobileNumber, code);

        //4.将已发送短信的手机号和验证码存入redis并设置过期时间
        redisService.set(RedisKey.USER_MOBILE_SMSCODE+mobileNumber,code,300L);

        //5.返回验证码
        return code;
    }

    /**
     * @Author zhangyong
     * @Description //验证手机验证码
     * @Date 下午 4:57 2019/9/9 0009
     * @Param
     * @return
     */
    public void validSmsCode(String mobileNumber, String smsCode) {
        //1.允许不验证手机验证码 直接return
        if (allowNotVerifySmsCode) {
            return;
        }

        //2.验证码为2017 直接return
        if ("2017".equals(smsCode)) {
            return;
        }

        //3.验证码是否失效
        if(!redisService.hasKey(RedisKey.USER_MOBILE_SMSCODE+mobileNumber)){
            throw new IllegalArgumentException("验证码已失效，请先发送验证码");
        }

        //4.判断code是否正确
        String code = (String)redisService.get(RedisKey.USER_MOBILE_SMSCODE+mobileNumber);
        if (!smsCode.equals(code)) {
            throw new IllegalArgumentException("验证码错误");
        }
    }

    /**
     * @Author zhangyong
     * @Description //生成验证码(可指定长度,目前为4位)
     * @Date 上午 10:29 2019/9/6 0006
     * @Param
     * @return
     */
    private String generateCode(int len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(r.nextInt(9));
        }
        return sb.toString();
    }



    @Override
    @Transactional
    public UserVo register(UserRegisterReqDto userRegisterReqDto) {

        //2.创建用户
        //(1)手机号是否存在
        String mobileNumber = userRegisterReqDto.getPrincipalMobile();
        User userByMobileNum = userMapper.selectOne(
                select(UserDynamicSqlSupport.id, UserDynamicSqlSupport.mobileNumber)
                        .from(UserDynamicSqlSupport.user)
                        .where(UserDynamicSqlSupport.mobileNumber, isEqualTo(mobileNumber))
                        .build()
                        .render(RenderingStrategies.MYBATIS3));
        if(!ObjUtil.isEmpty(userByMobileNum)){
            throw new IllegalArgumentException("手机号已存在,请重新输入");
        }

        //(2)登录名是否存在
        String username = userRegisterReqDto.getUsername();
        User userByName = userMapper.selectOne(
                select(UserDynamicSqlSupport.id, UserDynamicSqlSupport.username)
                        .from(UserDynamicSqlSupport.user)
                        .where(UserDynamicSqlSupport.username, isEqualTo(username))
                        .build()
                        .render(RenderingStrategies.MYBATIS3));
        if(!ObjUtil.isEmpty(userByName)){
            throw new IllegalArgumentException("用户名 "+username+" 已存在,请重新输入");
        }

        //(2).设置属性
        User user = VoPoConverterUtil.copyProperties(userRegisterReqDto, User.class);
        Long id = new IdWorker().nextId();
        Long curTime = System.currentTimeMillis();
        String pwd = userRegisterReqDto.getPassword();
        user.setId(id)
            .setDelFlag((byte)0)
            .setPassword(SHA256Util.getSHA256Salt(pwd, pwdSalt))
            .setMobileNumber(mobileNumber)
            .setStatus((byte)1)
            .setUserSex((byte)1)
            .setRegType(7L)
            .setRoleKey("Admin")
            .setRoleId(1503682070533906432L)
            .setCreateTime(curTime);
        //(3).保存用户信息
        userMapper.insert(user);

        //(4).保存用户-角色中间表
        UserRole userRole =  UserRole.builder()
                .userId(id)
                .roleId(1503682070533906432L)
                .createTime(curTime)
                .build();
        userRoleMapper.insert(userRole);

        //4.生成UserVo对象并返回
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setNickName(user.getName());
        userVo.setUsername(user.getUsername());
        userVo.setUserSex(user.getUserSex());
        userVo.setMobileNumber(user.getMobileNumber());
        userVo.setRegType(user.getRegType());

        //5.注册成功，清除已登录者的手机号，便于下次再次注册
        redisService.remove(RedisKey.USER_MOBILE_SMSCODE+user.getMobileNumber());

        //6.返回userVo
        return userVo;
    }

    @Override
    public UserVo updateRegister(UserRegisterReqDto userRegisterReqDto) {

        //(1)手机号是否存在
        String mobileNumber = userRegisterReqDto.getPrincipalMobile();
        User user = userMapper.selectByExampleOne()
                .where(UserDynamicSqlSupport.mobileNumber, isEqualTo(mobileNumber))
                .build()
                .execute();
        if(ObjUtil.isEmpty(user)){
            throw new IllegalArgumentException("手机号存在,不能更新注册信息");
        }

        //(3).修改完信息后,regType重新设置为7(申请中)
        userMapper.update(update(UserDynamicSqlSupport.user)
                .set(UserDynamicSqlSupport.regType).equalToWhenPresent(7L)
                .where(UserDynamicSqlSupport.id,isEqualTo(user.getId()))
                .build()
                .render(RenderingStrategies.MYBATIS3));


        //4.生成UserVo对象并返回
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setNickName(user.getName());
        userVo.setUsername(user.getUsername());
        userVo.setUserSex(user.getUserSex());
        userVo.setMobileNumber(user.getMobileNumber());
        userVo.setRegType(user.getRegType());
        //5.注册成功，清除已登录者的手机号，便于下次再次注册
        redisService.remove(RedisKey.USER_MOBILE_SMSCODE+user.getMobileNumber());

        //6.返回userVo
        return userVo;
    }

    @Override
    public UserVo login(UserLoginReqDto userLoginReqDto) {
        //1.根据用户名密码查询记录
        User user = queryByUsernameAndPassword(userLoginReqDto.getUsername(), userLoginReqDto.getPassword());
        if (ObjUtil.isEmpty(user)) {
            //保存登录日志
            AsyncJobManager.me().execute(AsyncFactory.saveLoginLog(userLoginReqDto.getUsername(),null, Constant.LOGIN_FAIL, MessageUtil.message("user.not.exists")));
            throw new IllegalArgumentException("用户名或密码错误");
        }


        //2.生成userVo对象并返回
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        userVo.setRegType(user.getRegType());
        userVo.setUserSex(user.getUserSex());
        userVo.setMobileNumber(user.getMobileNumber());
        userVo.setRoleId(user.getRoleId());
        userVo.setRoleKey(user.getRoleKey());

        return userVo;
    }

    @Override
    public Map<String, Object> queryUsernameExist(String username) {

        User user = userMapper.selectOne(
                select(UserDynamicSqlSupport.id, UserDynamicSqlSupport.username)
                        .from(UserDynamicSqlSupport.user)
                        .where(UserDynamicSqlSupport.username, isEqualTo(username))
                        .build()
                        .render(RenderingStrategies.MYBATIS3));
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("exist",false);
        if(!ObjUtil.isEmpty(user)) {
            resultMap.put("exist",true);
        }
        return resultMap;
    }


    @Override
    public List<PermsVo> getPermsVoListByUserId(Long id) {
        //1.管理员信息
        User user = userMapper.selectByExampleOne()
                .where(UserDynamicSqlSupport.id, isEqualTo(id))
                .build()
                .execute();

        if(ObjUtil.isEmpty(user)){
            throw new IllegalArgumentException("id为:"+id+"的管理员不存在");
        }

        //2.获取到该登陆者的角色信息
        UserRole userRole = userRoleMapper.selectByExampleOne()
                .where(UserRoleDynamicSqlSupport.userId, isEqualTo(user.getId()))
                .build()
                .execute();

        if(ObjUtil.isEmpty(userRole)){
            throw new IllegalArgumentException("该用户还未分配任何角色");
        }

        Long roleId = userRole.getRoleId();
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if(ObjUtil.isEmpty(role)){
            throw new IllegalArgumentException("id为:"+roleId+"的角色信息不存在");
        }

        //3.根据角色查询路由RouterVo(权限)列表树形结构列表;
        List<PermsVo> permsVoList = permissionService.getPermsVoListTreeDataByRoleId(roleId);
        return permsVoList;
    }


    @Override
    public void putTokenToRedis(String userId, String token) {
        redisService.set(RedisKey.USER_TOKEN_KEY+userId,token,expiration);
    }

    public User queryByUsernameAndPassword(String username, String password) {
        User user = userMapper.selectOne(
                select(UserDynamicSqlSupport.id,
                        UserDynamicSqlSupport.username,
                        UserDynamicSqlSupport.picUrl,
                        UserDynamicSqlSupport.userSex,
                        UserDynamicSqlSupport.mobileNumber,
                        UserDynamicSqlSupport.roleKey,
                        UserDynamicSqlSupport.roleId,
                        UserDynamicSqlSupport.regType,
                        UserDynamicSqlSupport.name
                )
                        .from(UserDynamicSqlSupport.user)
                        .where(UserDynamicSqlSupport.username, isEqualTo(username))
                        .and(UserDynamicSqlSupport.password, isEqualTo(password))
                        .build()
                        .render(RenderingStrategies.MYBATIS3));
        return user;
    }
}
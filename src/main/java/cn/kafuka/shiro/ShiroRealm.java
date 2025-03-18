package cn.kafuka.shiro;

import cn.kafuka.bo.po.*;
import cn.kafuka.bo.vo.UserVo;
import cn.kafuka.mapper.*;
import cn.kafuka.redis.RedisKey;
import cn.kafuka.redis.RedisService;
import cn.kafuka.util.JwtTokenUtil;
import cn.kafuka.util.ObjUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author: zhangyong
 * description: 2.自定义 权限配置(包含2个东西,哪些接口可以访问(Authorization)，怎样登录才算成功(Authentication)(验证用户名密码/验证码+密码等等))
 * @Date: 2019-12-11 11:03
 * @Param:
 * @Return:
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private RedisService redisService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;


    //需要配置支持JwtToken作为登录信息,替代默认的用户名和密码,因为系统使用了jwt作为验证的工具，而非session
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权逻辑(哪些接口能够访问，向SimpleAuthorizationInfo设置该登录者对应的角色和权限字符串列表，
    // 权限字符串对应Controller类或方法上的注解，@RequiresRoles("Admin")或者@RequiresPermissions("permission:list")中的 "Admin" 和 permission:list",
    // 而"Admin" 和 permission:list"这些权限字符串配置在数据库role表和permission表中)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.初始化一个权限控制信息对象，以便装入指定的权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //2.获取登陆者token()
        String token = (String) principalCollection.getPrimaryPrincipal();
        UserVo userVo = jwtTokenUtil.getUserDetailsFromToken(token);
        User user = userMapper.selectByExampleOne()
                .where(UserDynamicSqlSupport.id, isEqualTo(userVo.getId()))
                .build()
                .execute();

        if(ObjUtil.isEmpty(user)){
            throw new IllegalArgumentException("该用户没有权限");
        }

        //3.获取到该登陆者的角色信息
        UserRole userRole = userRoleMapper.selectByExampleOne()
                .where(UserRoleDynamicSqlSupport.userId, isEqualTo(user.getId()))
                .build()
                .execute();

        if(ObjUtil.isEmpty(userRole)){
            throw new IllegalArgumentException("该用户还未分配任何角色");
        }
        Long roleId = userRole.getRoleId();
        Role role = roleMapper.selectByExampleOne()
                .where(RoleDynamicSqlSupport.id, isEqualTo(roleId))
                .build()
                .execute();
        String roleKey = role.getRoleKey();

        //3.获取该角色拥有的权限列表
        List<Permission> permissionList;
        if("SuperAdmin".equals(roleKey)){
            //--1.超级管理员拥有所有权限
            permissionList = permissionMapper.selectMany(
                    SelectDSL.select(PermissionDynamicSqlSupport.perms)
                            .from(PermissionDynamicSqlSupport.permission)
                            .where(PermissionDynamicSqlSupport.perms, isNotNull())
                            .and(PermissionDynamicSqlSupport.perms, isNotEqualTo(""))
                            .build()
                            .render(RenderingStrategies.MYBATIS3));
        }else {
            //--1.查询出权限ID列表
            List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample()
                    .where(RolePermissionDynamicSqlSupport.roleId, isEqualTo(roleId))
                    .build()
                    .execute();
            if(ObjUtil.isEmpty(rolePermissionList)){
                throw new IllegalArgumentException("该角色还未赋予任何权限");
            }

            List<Long> idList = rolePermissionList.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());

            //--2.查询出权限列表
            permissionList = permissionMapper.selectMany(
                    SelectDSL.select(PermissionDynamicSqlSupport.perms)
                            .from(PermissionDynamicSqlSupport.permission)
                            .where(PermissionDynamicSqlSupport.id, isIn(idList))
                            .and(PermissionDynamicSqlSupport.perms, isNotNull())
                            .and(PermissionDynamicSqlSupport.perms, isNotEqualTo(""))
                            .build()
                            .render(RenderingStrategies.MYBATIS3));
        }

        //4.向权限控制信息对象，设置该用户的角色信息和权限信息
        //--1.角色信息
        authorizationInfo.addRole(roleKey);
        //--2.权限信息
        for(Permission p : permissionList){
            authorizationInfo.addStringPermission(p.getPerms());
        }

        //5.返回设置好的权限控制信息对象
        return authorizationInfo;
    }

    //用户认证逻辑(怎样登录才算成功,此处是有token即认为成功,因为登录验证逻辑是在gateway中的AuthFilter实现的，此处不许要再次验证了)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.获取token(这里的 token 是从 JwtFilter 的 executeLogin 方法传递过来的)
        String token = (String) authenticationToken.getPrincipal();

        //2.组合一个验证信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, "shiro_realm");
        return info;
    }
}

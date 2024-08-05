package cn.kafuka.jobManager.factory;

import cn.kafuka.bo.po.HttpPushLog;
import cn.kafuka.bo.po.LoginLog;
import cn.kafuka.bo.po.OperateLog;
import cn.kafuka.constant.Constant;
import cn.kafuka.dao.HttpPushLogDao;
import cn.kafuka.dao.LoginLogDao;
import cn.kafuka.dao.OperateLogDao;
import cn.kafuka.redis.RedisService;
import cn.kafuka.util.*;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.TimerTask;


//异步工厂（产生任务用）
public class AsyncFactory
{
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask saveLoginLog(final String username, final String status, final String message,
                                         final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
        final String ip = IpUtil.getIpAddr(ServletUtil.getRequest());
        return new TimerTask()
        {
            @Override
            public void run()
            {
                String address = IpUtil.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtil.getBlock(ip));
                s.append(address);
                s.append(LogUtil.getBlock(username));
                s.append(LogUtil.getBlock(status));
                s.append(LogUtil.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                LoginLog loginLog = LoginLog.builder()
                        .loginTime(System.currentTimeMillis())
                        .loginDate(DateUtil.getCurTimeString())
                        .userName(username)
                        .ip(ip)
                        .location(address)
                        .browser(browser)
                        .os(os)
                        .msg(message)
                        .build();
                // 日志状态
                if (StringUtils.endsWithAny(status, Constant.LOGIN_SUCCESS, Constant.LOGOUT, Constant.REGISTER))
                {
                    loginLog.setStatus(Constant.SUCCESS);
                }
                else if (Constant.LOGIN_FAIL.equals(status))
                {
                    loginLog.setStatus(Constant.FAIL);
                }
                // 插入数据
                SpringUtil.getBean(LoginLogDao.class).insert(loginLog);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask saveOperLog(final OperateLog operLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                try {
                    operLog.setLocation(IpUtil.getRealAddressByIP(operLog.getIp()));
                }catch (NullPointerException e){
                    //空指针异常，代码日志是ServiceLog,不用获取ip
                }
                SpringUtil.getBean(OperateLogDao.class).insert(operLog);
            }
        };
    }

    /**
     * HTTP推送日志记录
     *
     * @param httpPushLog HTTP推送日志记录
     * @return 任务task
     */
    public static TimerTask saveHttpPushLog(final HttpPushLog httpPushLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {

                //1.将该设备，该类型 上一条数据的latestData改为0
                Criteria criteria = Criteria.where("taskNo").is(httpPushLog.getTaskNo());
                criteria.and("latestData").is((byte)1);
                Query query = new Query(criteria);
                Update update = new Update().set("latestData", (byte)0);
                HttpPushLogDao httpPushLogDao = SpringUtil.getBean(HttpPushLogDao.class);
                httpPushLogDao.update(query, update);
                httpPushLogDao.insert(httpPushLog);

                //4.更新设备推送状态(推送成功则存入redis)
                Byte status = httpPushLog.getStatus();
                if(status == 1){
                    RedisService redisService = SpringUtil.getBean(RedisService.class);
                    redisService.hmSet("httpPushStatus", httpPushLog.getTaskNo(), String.valueOf(1));
                }
            }
        };
    }
}

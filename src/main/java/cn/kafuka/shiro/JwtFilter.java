package cn.kafuka.shiro;

import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.bo.vo.UserVo;
import cn.kafuka.enums.ResponseStatus;
import cn.kafuka.redis.RedisKey;
import cn.kafuka.redis.RedisService;
import cn.kafuka.util.JwtTokenUtil;
import cn.kafuka.util.ObjUtil;
import cn.kafuka.util.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: zhangyong
 * description: jwt过滤器(只需要有token即可正常访问接口,因为gateway已经验证过一次，无需再次验证token)
 * @Date: 2019-12-11 12:35
 * @Param:
 * @Return:
 */
@Slf4j
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static final String TOKEN = "token";


    //是否允许通过,向shiro登录注册成功后即可再允许访问接口
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        /*HttpServletRequest hr = (HttpServletRequest)request;

        String requestURI = hr.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        log.info("step1 ---> 当前请求的路径:{}",requestURI);
        log.info("step2 ---> 当前请求的ip地址:{}",remoteAddr);*/

        JwtTokenUtil jwtTokenUtil = SpringUtil.getBean("jwtTokenUtil", JwtTokenUtil.class);
        RedisService redisService = SpringUtil.getBean("redisService", RedisService.class);

        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(TOKEN);
        if(ObjUtil.isEmpty(token)){
            ResultVo resultVo  = ResultVo.fail(ResponseStatus.TOKEN_MISSING);
            unifiedReturnInfo(resp,resultVo);
            return false;
        }

        UserVo userVo= jwtTokenUtil.getUserDetailsFromToken(token);
        if(ObjUtil.isEmpty(userVo)){
            ResultVo resultVo  = ResultVo.fail(ResponseStatus.TOKEN_INVALID);
            unifiedReturnInfo(resp,resultVo);
            return false;
        }

        //(2).redis中没有该账户的token
        Long id = userVo.getId();
        String userId = String.valueOf(id);
        if (!redisService.hasKey(RedisKey.USER_TOKEN_KEY+userId)) {
            ResultVo resultVo  = ResultVo.fail(ResponseStatus.TOKEN_TIMEOUT);
            unifiedReturnInfo(resp,resultVo);
            return false;
        }

        Boolean executeLogin = executeLogin(request, response);
        return executeLogin;
    }


    /**
     * @Description 统一的返回结构 返回给接口调用者
     */
    private void unifiedReturnInfo(HttpServletResponse resp, ResultVo resultVo){
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter out = resp.getWriter();
            out.write(JSON.toJSONString(resultVo, SerializerFeature.WriteMapNullValue));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //向shiro登录注册用户信息(每次shiro就会去读取用户信息的角色和权限列表，以比对是否具有接口或资源访问权限，没有即抛出异常，系统通过全局异常增强器捕捉该异常即可)
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(TOKEN);
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }


    //跨域支持
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
        httpServletResponse.setStatus(HttpStatus.OK.value());
        return false;
        }
        return super.preHandle(request, response);
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");//先从nginx自定义配置获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-real-ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }



}

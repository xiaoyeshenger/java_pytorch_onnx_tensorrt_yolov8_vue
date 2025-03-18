package cn.kafuka.util;

import com.alibaba.fastjson.JSONObject;
import cn.kafuka.bo.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

public class UserUtil {

    @SneakyThrows
    public static UserVo getUserVo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = URLDecoder.decode(request.getHeader("token"), "utf-8");
        JwtTokenUtil jwtTokenUtil = SpringUtil.getBean(JwtTokenUtil.class);
        UserVo userVo = jwtTokenUtil.getUserDetailsFromToken(token);
        return userVo;
    }
}

package cn.kafuka.util;

import com.alibaba.fastjson.JSONObject;
import cn.kafuka.bo.vo.UserVo;
import lombok.SneakyThrows;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;


public class UserUtil {

    @SneakyThrows
    public static UserVo getUserVo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userInfo = URLDecoder.decode(request.getHeader("userInfo"), "utf-8");
        UserVo userVo = JSONObject.parseObject(userInfo,UserVo.class);
        return userVo;
    }
}

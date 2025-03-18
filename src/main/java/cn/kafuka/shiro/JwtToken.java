package cn.kafuka.shiro;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * @Author: zhangyong
 * description:
 *              --1.现在的方法如下:
 *              JwtToken实现AuthenticationToken，以便登录信息可以提交JwtToken，目的是将该用户的信息向shiro登录注册
 *              具体方法是：getSubject(request, response).login(jwtToken)，JwtFilter类中的executeLogin方法实现
 *
 *              --2.之前默认的方法如下，login(xx);xx只能传UsernamePasswordToken类型的参数
 *              UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(managerLoginReqDto.getUsername(), managerLoginReqDto.getPassword());
                SecurityUtils.getSubject().login(usernamePasswordToken);
 * @Date: 2019-12-11 12:39
 * @Param:
 * @Return:
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

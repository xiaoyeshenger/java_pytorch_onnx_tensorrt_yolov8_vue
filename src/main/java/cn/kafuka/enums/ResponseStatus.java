package cn.kafuka.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * @Author: zhangyong
 * description: 响应状态枚举
 * @Date: 2020-03-09 19:24
 * @Param:
 * @Return:
 */
@Getter
@NoArgsConstructor
public enum ResponseStatus {


    /**
     * 请求失败
     * */

    FAIL(0,"FAIL"),

    /**
     * 请求成功
     * */
    OK(200,"SUCCESS"),

    /**
     * 服务器异常
     * */
    ERROR(500,"服务异常，请联系管理员！"),

    /**
     * 参数错误
     * */
    PARAM_ERROR(400,"非法参数！"),

    /**
     * 拒绝访问
     * */
    FORBIDDEN(403,"拒绝访问！"),


    /**
     * 用户相关错误
     * */
    NO_LOGIN(1001, "未登录或登陆失效！"),
    VEL_CODE_ERROR(1002, "验证码错误！"),
    USERNAME_EXIST(1003,"该手机号已注册！"),
    USERNAME_PASS_ERROR(1004,"手机号或密码错误！"),
    TWO_PASSWORD_DIFF(1005, "两次输入的新密码不匹配!"),
    OLD_PASSWORD_ERROR(1006, "旧密码不匹配!"),

    //token
    TOKEN_MISSING(2001, "token缺失"),
    TOKEN_INVALID(2002, "token校验失败"),
    TOKEN_TIMEOUT(2003, "token失效"),

    /**
     * 其他通用错误
     * */
    PASSWORD_ERROR(88001,"密码错误！");

    //对应枚举的参数
    public   Integer code;
    public  String msg;

    private ResponseStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseStatus setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseStatus setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}

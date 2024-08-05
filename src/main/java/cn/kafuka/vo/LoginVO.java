package cn.kafuka.vo;

import cn.kafuka.entity.SysUser;

import java.io.Serializable;

/**
 * @Author: 三分恶
 * @Date: 2021/1/18
 * @Description: 登录VO
 **/

public class LoginVO implements Serializable {
    private Integer id;
    private String token;
    private SysUser sysUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SysUser getUser() {
        return sysUser;
    }

    public void setUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}

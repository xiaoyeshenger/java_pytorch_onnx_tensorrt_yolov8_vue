package cn.kafuka.bo.po;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//用户
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    //ID
    private Long id;

    //姓名
    private String name;

    //登录名
    private String username;

    //密码
    private String password;

    //性别(0=男,1=女,2=未知)
    private Byte userSex;

    //邮箱
    private String email;

    //手机号
    private String mobileNumber;

    //头像
    private String picUrl;

    //角色
    private String roleKey;

    //角色ID
    private Long roleId;

    //状态(0=停用,1=启用)
    private Byte status;

    //用户注册类型(正式/申请中/未通过审核)
    private Long regType;

    //删除标志(0=未删除,1=已删除)
    private Byte delFlag;

    //创建时间
    private Long createTime;

    //最后登录IP
    private String loginIp;

    //最后登录时间
    private Long loginTime;

}
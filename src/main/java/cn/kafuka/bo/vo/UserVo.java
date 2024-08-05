package cn.kafuka.bo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/*
 * @Author: zhangyong
 * description: 用户
 * @Date: 2020-03-22 12:13
 * @Param:
 * @Return:
 */
@Data
public class UserVo {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户注册状态(7:申请中,8:正式用户,9:未通过)")
    private Long regType;

    @ApiModelProperty(value = "头像")
    private String picUrl;

    @ApiModelProperty(value = "用户性别，0：男，1：女")
    private Byte userSex;

    @ApiModelProperty(value = "角色")
    private String roleKey;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "电话号码")
    private String mobileNumber;
}

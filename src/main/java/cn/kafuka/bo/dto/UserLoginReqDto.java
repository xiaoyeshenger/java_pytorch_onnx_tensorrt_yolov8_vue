package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/*
 * @Author 张勇
 * @Description //UserReqDto
 * @Date 2021/05/21 18:43
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginReqDto extends BaseDto {

    @ApiModelProperty(name = "username", value = "登录名", example = "test",dataType="String")
    @NotNull(message = "登录名不能为空")
    private String username;

    @ApiModelProperty(name = "password", value = "密码", example = "xxx",dataType="String")
    @NotNull(message = "密码不能为空")
    private String password;
}
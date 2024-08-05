package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/*
 * @Author 张勇
 * @Description //UserReqDto
 * @Date 2021/02/04 14:49
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserPwdUpdateDto extends BaseDto {

    @ApiModelProperty(name = "userId", value = "用户ID不能为空", example = "1563254544564896325",dataType="Long")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty(name = "newPassword", value = "新密码", example = "zjejrrwr123",dataType="String")
    @NotBlank(message="新密码不能为空")
    @Length(min = 6,max = 30,message = "新密码不能低于6位数或超过30位数")
    private String newPassword;
}
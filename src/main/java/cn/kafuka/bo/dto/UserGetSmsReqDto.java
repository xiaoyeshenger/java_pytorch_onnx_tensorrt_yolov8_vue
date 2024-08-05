package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/*
 * @Author 张勇
 * @Description //UserGetSmsReqDto
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
public class UserGetSmsReqDto extends BaseDto {

    @ApiModelProperty(name = "mobileNumber", value = "手机号不能为空(手机号)", example = "18901013333",dataType="String")
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$",message = "电话号码格式不对")
    private String mobileNumber;

}
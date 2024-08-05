package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //LoginLogPageReqDto
 * @Date 2022/03/02 16:01
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
public class LoginLogPageReqDto extends PageReqDto {


    @ApiModelProperty(name = "parkId", value = "园区ID", example = "xxx",dataType="Long")
    @NotNull(message = "园区ID不能为空")
    private Long parkId;

    @ApiModelProperty(name = "userName", value = "用户名", example = "xxx",dataType="String")
    private String userName;

    @ApiModelProperty(name = "ip", value = "登录地址(ip)", example = "xxx",dataType="String")
    private String ip;

    @ApiModelProperty(name = "status", value = "登录状态(0=成功,1=失败)", example = "xxx",dataType="Byte")
    @NotNull(message = "登录状态(0=成功,1=失败)不能为空")
    private Byte status;

    @ApiModelProperty(name = "startTime", value = "登录开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "登录结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;
    

}
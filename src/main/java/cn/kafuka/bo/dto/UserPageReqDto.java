package cn.kafuka.bo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain=true)
public class UserPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    private Byte status;

    @ApiModelProperty(name = "regType", value = "注册类型(正式/申请中/未通过)", example = "9",dataType="Long")
    private Long regType;

    @ApiModelProperty(name = "startTime", value = "创建开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "创建结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}

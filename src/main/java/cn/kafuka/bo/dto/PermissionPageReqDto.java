package cn.kafuka.bo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class PermissionPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "name", value = "权限名称", example = "医保服务",dataType="String")
    private String name;

    @ApiModelProperty(name = "useType", value = "使用类型(APP/PC)", example = "18", dataType = "Long")
    @NotNull(message = "使用类型不能不能为空")
    private Long useType;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    private Byte status;

    @ApiModelProperty(name = "startTime", value = "创建开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "创建结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}



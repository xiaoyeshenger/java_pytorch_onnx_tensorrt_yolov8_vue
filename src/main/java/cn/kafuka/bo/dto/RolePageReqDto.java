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
public class RolePageReqDto extends PageReqDto {

    @ApiModelProperty(name = "parkId", value = "园区ID", example = "11451758690780233",dataType="Long")
    @NotNull(message = "园区ID不能为空")
    private Long parkId;

    @ApiModelProperty(name = "name", value = "角色名称", example = "角色名称",dataType="String")
    private String name;

    @ApiModelProperty(name = "roleKey", value = "角色标识", example = "Admin",dataType="String")
    private String roleKey;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    private Byte status;

    @ApiModelProperty(name = "startTime", value = "创建开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "创建结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}

package cn.kafuka.bo.dto;


import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class DataDictReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "id", example = "1391784986742280192",dataType="Long")
    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "name", value = "名称", example = "增加",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(name = "orderNum", value = "排序", example = "3",dataType="Integer")
    @NotNull(message = "排序不能为空")
    private Integer orderNum;

    @ApiModelProperty(name = "parentCode", value = "父级编码", example = "5001",dataType="Integer")
    @NotNull(message = "父级编码不能为空")
    private Long parentId;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    @NotNull(message = "状态不能为空")
    private Byte status;
}
package cn.kafuka.bo.dto;


import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


/*
 * @Author 张勇
 * @Description //RoleReqDto
 * @Date 2021/05/24 11:26
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "id", example = "123",dataType="Long")
    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "name", value = "角色名称", example = "角色名称",dataType="String")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty(name = "roleKey", value = "角色标识", example = "Admin",dataType="String")
    @NotBlank(message = "角色标识不能为空")
    private String roleKey;

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "1",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;

    @ApiModelProperty(name = "useType", value = "使用类型(APP/PC)", example = "PC",dataType="Long")
    @NotNull(message = "使用类型不能为空")
    private Long useType;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    @NotNull(message = "状态不能为空")
    private Byte status;

    @ApiModelProperty(name = "parkId", value = "园区ID", example = "11451758690780233",dataType="Long")
    @NotNull(message = "园区ID不能为空")
    private Long parkId;

    @ApiModelProperty(name = "permsList", value = "权限列表", example = "[13,14,15,16]",dataType="Array")
    @NotNull(message = "权限列表不能为空")
    @NotEmpty(message = "权限列表不能为空")
    private List<Long> permsList;
}
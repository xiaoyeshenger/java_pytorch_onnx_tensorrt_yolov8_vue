package cn.kafuka.bo.dto;


import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/*
 * @Author 张勇
 * @Description //PermissionReqDto
 * @Date 2021/05/24 11:18
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class PermissionReqDto extends BaseDto {

    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "parentId", value = "父级权限id(如果为顶级菜单,传0即可)", example = "13",dataType="Long")
    @NotNull(message = "父级权限id不能为空")
    private Long parentId;

    @ApiModelProperty(name = "permsType", value = "权限类型(目录/菜单/按钮(具体查看数据字典))", example = "Catalog",dataType="Long")
    @NotNull(message = "权限类型不能为空")
    private Long permsType;

    @ApiModelProperty(value = "icon访问地址/名称")
    private String icon;

    @ApiModelProperty(name = "name", value = "权限名称", example = "广告管理",dataType="String")
    @NotNull(message = "权限名称不能为空")
    private String name;

    @ApiModelProperty(name = "routerName", value = "路由名称", example = "MyPark",dataType="String")
    private String routerName;

    @ApiModelProperty(name = "orderNum", value = "排序", example = "3",dataType="Integer")
    @NotNull(message = "排序不能为空")
    private Integer orderNum;

    @ApiModelProperty(name = "useType", value = "使用类型(APP/PC)", example = "17", dataType = "Long")
    @NotNull(message = "使用类型不能为空")
    private Long useType;

    @ApiModelProperty(name = "linkType", value = "链接类型(内部接口（19）/外部链接(20))", example = "19",dataType="Long")
    @NotNull(message = "链接类型不能为空")
    private Long linkType;

    @ApiModelProperty(name = "component", value = "组件路径", example = "myPark/list/index",dataType="String")
    private String component;

    @ApiModelProperty(name = "path", value = "跳转路径", example = "/system",dataType="String")
    private String path;

    @ApiModelProperty(name = "status", value = "状态", example = "1",dataType="Byte")
    @NotNull(message = "状态不能不能为空")
    private Byte status;

    @ApiModelProperty(value = "权限字符")
    private String perms;

}
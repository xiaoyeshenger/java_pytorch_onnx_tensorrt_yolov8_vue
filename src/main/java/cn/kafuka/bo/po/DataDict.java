package cn.kafuka.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.io.Serializable;


/**
 * @Author: zhangyong
 * description: 数据字典
 * @Date: 2021-02-01 09:15
 * @Param:
 * @Return:
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class DataDict implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "中文")
    private String name;

    @ApiModelProperty(value = "英文")
    private String value;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "父级Id")
    private Long parentId;

    @ApiModelProperty(value = "能否多选(0不能，1能)")
    private Boolean multiple;

    @ApiModelProperty(value = "是否选中")
    private Boolean selected;

    @ApiModelProperty(value = "是否和同类型互斥,0表示不限,1表示其他")
    private Integer mutex;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}

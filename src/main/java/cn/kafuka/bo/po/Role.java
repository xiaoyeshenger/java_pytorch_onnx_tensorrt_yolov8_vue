package cn.kafuka.bo.po;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//角色
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    //ID
    private Long id;

    //角色名称
    private String name;

    //角色权限
    private String roleKey;

    //角色排序
    private Integer orderNum;

    //使用类型
    private Long useType;

    //角色状态("0=停用,1=正常)
    private Byte status;

    //删除标志(0=未删除,1=已删除)
    private Byte delFlag;

    //创建时间
    private Long createTime;

    //园区ID
    private Long parkId;

}
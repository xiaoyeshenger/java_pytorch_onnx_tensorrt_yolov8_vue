package cn.kafuka.bo.po;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//角色-模型(中间表)
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel implements Serializable {

    //id
    private Long id;

    //角色id
    private Long roleId;

    //模型id
    private Long modelId;

    //模型no
    private String modelNo;

    //模型名称
    private String modelName;
}
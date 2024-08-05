package cn.kafuka.bo.po;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//角色-权限(中间表)
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission implements Serializable {

    //id
    private Long id;

    //角色id
    private Long roleId;

    //权限(菜单)id
    private Long permissionId;

}
package cn.kafuka.bo.po;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//用-角色(中间表)
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {

    //id
    private Long id;

    //管理员id
    private Long userId;

    //角色id
    private Long roleId;

    //创建时间
    private Long createTime;

}
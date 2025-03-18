package cn.kafuka.bo.po;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//角色-任务(中间表)
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleTask implements Serializable {

    //id
    private Long id;

    //角色id
    private Long roleId;

    //任务id
    private Long taskId;

    //任务no
    private String taskNo;

    //任务名称
    private String taskName;

}
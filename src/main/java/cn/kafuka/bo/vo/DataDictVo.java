package cn.kafuka.bo.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.util.List;


/*
 * @Author: zhangyong
 * description: 数据字典vo
 * @Date: 2021-02-01 10:26
 * @Param:
 * @Return:
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
public class DataDictVo extends BaseVo {
    //使用@Builder时，@Builder会覆盖默认的构造器，所以必须加上@Tolerate和该类的空构造函数
    @Tolerate
    DataDictVo() {}

    //ID
    private Long id;

    //中文
    private String name;

    //英文
    private String value;

    //父级ID
    private Long parentId;

    //父级value
    private String parentValue;

    //能否多选(0不能，1能)
    private Boolean multiple;

    //是否和同类型互斥,0表示不限,1表示其他
    private Integer mutex;

    //是否选中
    private Boolean selected;

    //状态(0=停用,1=启用)
    private Byte status;

    //排序
    private Integer orderNum;

    //子集字典
    private List<DataDictVo> childList;
}

package cn.kafuka.bo.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Builder
@Accessors(chain=true)
public class BeanVo extends BaseVo {

    @Tolerate
    BeanVo() {}

    //字段名
    private String  fieldName;

    //字段中文名
    private String  fieldValue;

    //字段类型
    private String  fieldType;

}

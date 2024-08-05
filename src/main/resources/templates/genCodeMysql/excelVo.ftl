package ${basePackageExcel};


import ${basePackage}.bo.dto.BaseDto;
import ${basePackage}.valid.ValidationGroup;
import ${basePackage}.annotation.FieldDupValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}ExcelVo
 * @Date ${date}
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ${pojoNameUpperCamel}ExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    <#list fileNameAndTypeList as beanVo>
    <#if beanVo.fieldType =="enum">
    @ExcelProperty(value = "${beanVo.fieldValue}",index = 1)
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${'${beanVo.fieldName}'?cap_first}")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    @EnumValidAnnotation(message = "${beanVo.fieldValue}输入错误",target = ${'${beanVo.fieldName}'?cap_first}.class )
    private String ${beanVo.fieldName};
    ${"\n"}
    <#elseif beanVo.fieldType?contains("List")>
    @ExcelProperty(value = "${beanVo.fieldValue}",index = 1)
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="List")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    @NotEmpty(message = "${beanVo.fieldValue}不能为空")
    private ${beanVo.fieldType} ${beanVo.fieldName};
    ${"\n"}
    <#else>
    @ExcelProperty(value = "${beanVo.fieldValue}",index = 1)
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${beanVo.fieldType}")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    private ${beanVo.fieldType} ${beanVo.fieldName};
    ${"\n"}
    </#if>
    </#list>
}
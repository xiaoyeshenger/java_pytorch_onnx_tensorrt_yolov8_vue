package ${basePackageDto};


import ${basePackage}.bo.dto.BaseDto;
import ${basePackage}.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}ReqDto
 * @Date ${date}
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ${pojoNameUpperCamel}ExcelExportReqDto extends BaseDto {

    <#list fileNameAndTypeList as beanVo>
    <#if beanVo.fieldType =="enum">
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${'${beanVo.fieldName}'?cap_first}")
    @EnumValidAnnotation(message = "${beanVo.fieldValue}输入错误",target = ${'${beanVo.fieldName}'?cap_first}.class )
    private String ${beanVo.fieldName};
    ${"\n"}
    <#elseif beanVo.fieldType?contains("List")>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="List")
    private ${beanVo.fieldType} ${beanVo.fieldName};
    ${"\n"}
    <#else>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${beanVo.fieldType}")
    private ${beanVo.fieldType} ${beanVo.fieldName};
    ${"\n"}
    </#if>
    </#list>
}
package ${basePackageDto};


import ${basePackage}.bo.dto.PageReqDto;
import ${basePackage}.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}PageReqDto
 * @Date ${date}
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class ${pojoNameUpperCamel}PageReqDto extends PageReqDto {

    <#list fileNameAndTypeList as beanVo>
    <#if beanVo.fieldType =="enum">
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${'${beanVo.fieldName}'?cap_first}")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    @EnumValidAnnotation(message = "${beanVo.fieldValue}输入错误",target = ${'${beanVo.fieldName}'?cap_first}.class )
    private String ${beanVo.fieldName};
    ${"\n"}
    <#elseif beanVo.fieldType?contains("List")>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="List")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    @NotEmpty(message = "${beanVo.fieldValue}不能为空")
    private ${beanVo.fieldType} ${beanVo.fieldName};
    ${"\n"}
    <#else>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${beanVo.fieldType}")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    private ${beanVo.fieldType} ${beanVo.fieldName};
    ${"\n"}
    </#if>
    </#list>
}
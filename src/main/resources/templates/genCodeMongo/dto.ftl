package ${basePackageDto};


import io.swagger.annotations.ApiModelProperty;
import ${basePackage}.bo.dto.BaseDto;
import ${basePackage}.valid.ValidationGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class ${pojoNameUpperCamel}ReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "id", example = "123",dataType="Long")
    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private String id;

    <#list fileNameAndTypeList as beanVo>
    <#if beanVo.fieldType =="enum">
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${'${beanVo.fieldName}'?cap_first}")
    @NotNull(message = "${beanVo.fieldName}不能为空")
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
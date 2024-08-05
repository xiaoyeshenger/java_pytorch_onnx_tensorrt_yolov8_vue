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
  <#if beanVo.fieldName !="id" && beanVo.fieldName !="createTime">
    <#if beanVo.fieldName?contains("Time")>
    @ApiModelProperty(name = "start${'${beanVo.fieldName}'?cap_first}", value = "${beanVo.fieldValue}开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long start${'${beanVo.fieldName}'?cap_first};

    @ApiModelProperty(name = "end${'${beanVo.fieldName}'?cap_first}", value = "${beanVo.fieldValue}结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long end${'${beanVo.fieldName}'?cap_first};

    <#else>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${beanVo.fieldType}")
    private ${beanVo.fieldType} ${beanVo.fieldName};
    </#if>
  </#if>
${"\n"}
</#list>
}
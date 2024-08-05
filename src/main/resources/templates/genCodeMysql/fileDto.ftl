package ${basePackageDto};


import ${basePackage}.bo.dto.BaseDto;
import ${basePackage}.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

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

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

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
  <#if beanVo.fieldName !="id" && beanVo.fieldName !="createTime">
   <#if beanVo.fieldName?contains("Time")>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${beanVo.fieldType}")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private ${beanVo.fieldType} ${beanVo.fieldName};
   <#elseif beanVo.fieldName?contains("idNum")>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${beanVo.fieldType}")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    @Pattern(regexp = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)$",
    message = "身份证号码格式不对")
    private ${beanVo.fieldType} ${beanVo.fieldName};
   <#elseif beanVo.fieldName?contains("mobile")>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${beanVo.fieldType}")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$",message = "电话号码格式不对")
    private ${beanVo.fieldType} ${beanVo.fieldName};
   <#elseif beanVo.fieldName?contains("atus")>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${beanVo.fieldType}")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    @Min(value = 0,message = "状态只能为0或1")
    @Max(value = 1,message = "状态只能为0或1")
    private ${beanVo.fieldType} ${beanVo.fieldName};
   <#else>
    @ApiModelProperty(name = "${beanVo.fieldName}", value = "${beanVo.fieldValue}", example = "xxx",dataType="${beanVo.fieldType}")
    @NotNull(message = "${beanVo.fieldValue}不能为空")
    private ${beanVo.fieldType} ${beanVo.fieldName};
  </#if>
 </#if>
 ${"\n"}
</#if>
</#list>
}
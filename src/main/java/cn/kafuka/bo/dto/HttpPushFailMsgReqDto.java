package cn.kafuka.bo.dto;


import cn.kafuka.bo.dto.BaseDto;
import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.*;
import java.util.List;


/*
 * @Author zhangyong
 * @Description //HttpPushFailMsgReqDto
 * @Date 2025/01/24 09:35
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class HttpPushFailMsgReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

 

    @ApiModelProperty(name = "type", value = "类型(911,915)", example = "xxx",dataType="Long")
    @NotNull(message = "类型(911,915)不能为空")
    private Long type;
 

    @ApiModelProperty(name = "typeName", value = "类型名称(推送模型库变化,推理结果,其他)", example = "xxx",dataType="String")
    @NotNull(message = "类型名称(推送模型库变化,推理结果,其他)不能为空")
    private String typeName;
 

    @ApiModelProperty(name = "modelNo", value = "模型序列号", example = "xxx",dataType="String")
    @NotNull(message = "模型序列号不能为空")
    private String modelNo;
 

    @ApiModelProperty(name = "modelName", value = "模型名称", example = "xxx",dataType="String")
    @NotNull(message = "模型名称不能为空")
    private String modelName;
 

    @ApiModelProperty(name = "customerNo", value = "客户号", example = "xxx",dataType="String")
    @NotNull(message = "客户号不能为空")
    private String customerNo;
 

    @ApiModelProperty(name = "customerName", value = "客户名称", example = "xxx",dataType="String")
    @NotNull(message = "客户名称不能为空")
    private String customerName;
 

    @ApiModelProperty(name = "httpReqUrl", value = "http请求地址", example = "xxx",dataType="String")
    @NotNull(message = "http请求地址不能为空")
    private String httpReqUrl;
 

    @ApiModelProperty(name = "httpReqHeader", value = "http请求头", example = "xxx",dataType="String")
    @NotNull(message = "http请求头不能为空")
    private String httpReqHeader;
 

    @ApiModelProperty(name = "httpReqParam", value = "http请求参数", example = "xxx",dataType="String")
    @NotNull(message = "http请求参数不能为空")
    private String httpReqParam;
 

    @ApiModelProperty(name = "httpResult", value = "http返回结果", example = "xxx",dataType="String")
    @NotNull(message = "http返回结果不能为空")
    private String httpResult;
 

    @ApiModelProperty(name = "errorMsg", value = "错误信息", example = "xxx",dataType="String")
    @NotNull(message = "错误信息不能为空")
    private String errorMsg;
 

    @ApiModelProperty(name = "pushTime", value = "推送时间", example = "xxx",dataType="Long")
    @NotNull(message = "推送时间不能为空")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long pushTime;
 

    @ApiModelProperty(name = "pushDate", value = "推送时间字符串", example = "xxx",dataType="String")
    @NotNull(message = "推送时间字符串不能为空")
    private String pushDate;
 

    @ApiModelProperty(name = "needPush", value = "是否需要再次推送(0=不需要,1=需要)", example = "xxx",dataType="Byte")
    @NotNull(message = "是否需要再次推送(0=不需要,1=需要)不能为空")
    private Byte needPush;
 

    @ApiModelProperty(name = "pushAmount", value = "已经推送次数", example = "xxx",dataType="Integer")
    @NotNull(message = "已经推送次数不能为空")
    private Integer pushAmount;
 

 

}
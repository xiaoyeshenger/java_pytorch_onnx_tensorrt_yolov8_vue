package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.*;

import java.util.List;


/**
 * @Author zhangyong
 * @Description //HttpPushFailMsgPageReqDto
 * @Date 2025/01/24 09:35
 * @Param
 * @return
 */
@Getter
@Setter
@Accessors(chain=true)
public class HttpPushFailMsgPageReqDto extends PageReqDto {



    @ApiModelProperty(name = "type", value = "类型(911,915)", example = "xxx",dataType="Long")
    private Long type;


    @ApiModelProperty(name = "typeName", value = "类型名称(推送模型库变化,推理结果,其他)", example = "xxx",dataType="String")
    private String typeName;


    @ApiModelProperty(name = "modelNo", value = "模型序列号", example = "xxx",dataType="String")
    private String modelNo;


    @ApiModelProperty(name = "modelName", value = "模型名称", example = "xxx",dataType="String")
    private String modelName;


    @ApiModelProperty(name = "customerNo", value = "客户号", example = "xxx",dataType="String")
    private String customerNo;


    @ApiModelProperty(name = "customerName", value = "客户名称", example = "xxx",dataType="String")
    private String customerName;


    @ApiModelProperty(name = "httpReqUrl", value = "http请求地址", example = "xxx",dataType="String")
    private String httpReqUrl;


    @ApiModelProperty(name = "httpReqHeader", value = "http请求头", example = "xxx",dataType="String")
    private String httpReqHeader;


    @ApiModelProperty(name = "httpReqParam", value = "http请求参数", example = "xxx",dataType="String")
    private String httpReqParam;


    @ApiModelProperty(name = "httpResult", value = "http返回结果", example = "xxx",dataType="String")
    private String httpResult;


    @ApiModelProperty(name = "errorMsg", value = "错误信息", example = "xxx",dataType="String")
    private String errorMsg;


    @ApiModelProperty(name = "startPushTime", value = "推送时间开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startPushTime;

    @ApiModelProperty(name = "endPushTime", value = "推送时间结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endPushTime;



    @ApiModelProperty(name = "pushDate", value = "推送时间字符串", example = "xxx",dataType="String")
    private String pushDate;


    @ApiModelProperty(name = "needPush", value = "是否需要再次推送(0=不需要,1=需要)", example = "xxx",dataType="Byte")
    private Byte needPush;


    @ApiModelProperty(name = "pushAmount", value = "已经推送次数", example = "xxx",dataType="Integer")
    private Integer pushAmount;




}
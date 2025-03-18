package cn.kafuka.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * http推送失败的消息
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class HttpPushFailMsg implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "类型(911,915)")
    private Long type;

    @ApiModelProperty(value = "类型名称(推送模型库变化,推理结果,其他)")
    private String typeName;

    @ApiModelProperty(value = "模型序列号")
    private String modelNo;

    @ApiModelProperty(value = "模型名称")
    private String modelName;

    @ApiModelProperty(value = "客户号")
    private String customerNo;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "http请求地址")
    private String httpReqUrl;

    @ApiModelProperty(value = "http请求头")
    private String httpReqHeader;

    @ApiModelProperty(value = "http请求参数")
    private String httpReqParam;

    @ApiModelProperty(value = "http返回结果")
    private String httpResult;

    @ApiModelProperty(value = "错误信息")
    private String errorMsg;

    @ApiModelProperty(value = "推送时间")
    private Long pushTime;

    @ApiModelProperty(value = "推送时间字符串")
    private String pushDate;

    @ApiModelProperty(value = "是否需要再次推送(0=不需要,1=需要)")
    private Byte needPush;

    @ApiModelProperty(value = "已经推送次数")
    private Integer pushAmount;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}
package cn.kafuka.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class PushLog implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "算法任务id")
    private String taskNo;

    @ApiModelProperty(value = "客户id")
    private String customerNo;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "模型号")
    private String modelNo;

    @ApiModelProperty(value = "模型名称")
    private String modelName;

    @ApiModelProperty(value = "http推送地址")
    private String httpReqUrl;

    @ApiModelProperty(value = "http请求头json字符串")
    private String httpReqHeader;

    @ApiModelProperty(value = "http请求体信息")
    private String httpReqBody;

    @ApiModelProperty(value = "http响应信息")
    private String httpRepInfo;

    @ApiModelProperty(value = "推送次数")
    private Integer pushAmount;

    @ApiModelProperty(value = "最近推送时间")
    private Long latestPushTime;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}
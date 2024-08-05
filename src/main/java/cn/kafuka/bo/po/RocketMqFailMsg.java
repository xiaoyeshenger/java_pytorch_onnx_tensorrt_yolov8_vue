package cn.kafuka.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * rocketMQ消费失败的消息
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RocketMqFailMsg implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "类型(推理结果,其他)")
    private Long type;

    @ApiModelProperty(value = "关键字(可以是对象的ID)")
    private String objKey;

    @ApiModelProperty(value = "关键字(可以是对象的ID)")
    private Integer reconsumeTimes;

    @ApiModelProperty(value = "消息ID")
    private String msgId;

    @ApiModelProperty(value = "消息内容")
    private String msgBody;

    @ApiModelProperty(value = "队列ID")
    private Integer queueId;

    @ApiModelProperty(value = "队列的偏移量")
    private Long queueOffset;

    @ApiModelProperty(value = "提交日志的偏移量")
    private Long commitLogOffset;

    @ApiModelProperty(value = "brokerName")
    private String brokerName;

    @ApiModelProperty(value = "服务器信息")
    private String bornHostString;

    @ApiModelProperty(value = "创建时间字符串")
    private String createDate;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}
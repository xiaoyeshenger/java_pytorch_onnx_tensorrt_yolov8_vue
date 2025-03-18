package cn.kafuka.bo.vo;

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
public class AlgorithmModelVo implements Serializable {

    @ApiModelProperty(value = "模型名称")
    private String name;

    @ApiModelProperty(value = "模型序列号")
    private String modelNo;

    @ApiModelProperty(value = "最近训练时间")
    private Long latestTrainingTime;

    @ApiModelProperty(value = "上线时间")
    private Long onlineTime;

    @ApiModelProperty(value = "标签列表")
    private String labelList;

    @ApiModelProperty(value = "状态")
    private Byte status;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}
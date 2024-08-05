package cn.kafuka.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmModel implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "模型名称")
    private String name;

    @ApiModelProperty(value = "模型key")
    private String modelKey;

    @ApiModelProperty(value = "模型序列号")
    private String modelNo;

    /**
     * (吸烟、打架、人行、人脸、安全帽、口罩、汽车、摩托车、电瓶车)
     */
    @ApiModelProperty(value = "算法类型")
    private Long algorithmType;

    /**
     * cnn、knn、svm
     */
    @ApiModelProperty(value = "核心技术")
    private String coreTech;

    @ApiModelProperty(value = "执行模型计算的shell key(yolo.py)")
    private String shellKey;

    @ApiModelProperty(value = "最近训练时间")
    private Long latestTrainingTime;

    @ApiModelProperty(value = "上线时间")
    private Long onlineTime;

    @ApiModelProperty(value = "置信度阈值")
    private Float confThreshold;

    @ApiModelProperty(value = "nms阈值")
    private Float nmsThreshold;

    @ApiModelProperty(value = "标签列表")
    private String labelList;

    @ApiModelProperty(value = "oos访问地址")
    private String oosUrl;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "状态")
    private Byte status;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}
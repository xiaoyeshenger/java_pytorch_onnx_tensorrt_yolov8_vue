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
public class AlgorithmTask implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务号")
    private String taskNo;

    @ApiModelProperty(value = "模型序列号")
    private String modelNo;

    @ApiModelProperty(value = "模型名称")
    private String algorithmModelName;

    @ApiModelProperty(value = "客户号")
    private String customerNo;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "原始视频基本信息(协议，带宽，图像大小)json")
    private String videoBaseInfo;

    @ApiModelProperty(value = "原始视频流播放地址")
    private String videoPlayUrl;

    @ApiModelProperty(value = "流媒体服务器地址")
    private String streamServerUrl;

    @ApiModelProperty(value = "实时计算的视频流播放地址")
    private String computingVideoPlayUrl;

    @ApiModelProperty(value = "实时推送的视频流播放地址")
    private String pushVideoPlayUrl;

    @ApiModelProperty(value = "跳帧数量(每隔多少帧检测一次)")
    private Integer skipFrame;

    @ApiModelProperty(value = "推送频率(每隔多少秒推送推理结果一次)")
    private Integer pushFrequency;

    @ApiModelProperty(value = "模型计算工作目录")
    private String workDir;

    @ApiModelProperty(value = "执行模型计算的shell key(yolo.py)")
    private String shellKey;

    @ApiModelProperty(value = "首次执行时间")
    private Long firstExecTime;

    @ApiModelProperty(value = "最近执行时间")
    private Long latestExecTime;

    @ApiModelProperty(value = "告警次数")
    private Integer alarmAmount;

    @ApiModelProperty(value = "最近告警时间")
    private Long latestAlarmTime;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "任务状态(0=停用,1=启用)")
    private Byte taskStatus;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}
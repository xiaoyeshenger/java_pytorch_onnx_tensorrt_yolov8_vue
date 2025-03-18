package cn.kafuka.bo.dto;


import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //AlgorithmTaskReqDto
 * @Date 2023/11/23 14:06
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAlgorithmTaskReqDto extends BaseDto {

    @ApiModelProperty(name = "taskNo", value = "任务序列号", example = "xxx",dataType="String")
    @NotNull(message = "任务序列号不能为空")
    private String taskNo;


    @ApiModelProperty(name = "modelNo", value = "模型序列号", example = "xxx",dataType="String")
    @NotNull(message = "模型序列号不能为空")
    private String modelNo;


    @ApiModelProperty(name = "customerNo", value = "客户号", example = "xxx",dataType="String")
    @NotNull(message = "客户号不能为空")
    private String customerNo;

    @ApiModelProperty(name = "videoPlayUrl", value = "原始视频流播放地址", example = "xxx",dataType="String")
    @NotNull(message = "原始视频流播放地址不能为空")
    private String videoPlayUrl;


    @ApiModelProperty(name = "taskName", value = "任务名称", example = "监测路口1",dataType="String")
    private String taskName;


    @ApiModelProperty(name = "skipFrame", value = "跳帧数量(每隔多少帧检测一次,默认为1 即不跳帧)", example = "3",dataType="Integer")
    private Integer skipFrame;


    @ApiModelProperty(name = "pushFrequency", value = "推送频率(每隔多少秒推送推理结果一次，默认为60s)", example = "120",dataType="Integer")
    private Integer pushFrequency;


    @ApiModelProperty(value = "置信度阈值")
    private Float confThreshold;


    @ApiModelProperty(value = "nms阈值")
    private Float nmsThreshold;


    @ApiModelProperty(name = "videoBaseInfo", value = "原始视频基本信息(协议(国标需要提供国标码等信息)，帧率，图像大小)json", example = "xxx",dataType="String")
    private String videoBaseInfo;

}
package cn.kafuka.excel;


import cn.kafuka.bo.dto.BaseDto;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.annotation.FieldDupValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //AlgorithmTaskExcelVo
 * @Date 2023/11/23 14:06
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmTaskExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "任务号",index = 1)
    @ApiModelProperty(name = "taskNum", value = "任务号", example = "xxx",dataType="String")
    @NotNull(message = "任务号不能为空")
    private String taskNum;
    

    @ExcelProperty(value = "模型id",index = 1)
    @ApiModelProperty(name = "algorithmModelId", value = "模型id", example = "xxx",dataType="Long")
    @NotNull(message = "模型id不能为空")
    private Long algorithmModelId;
    

    @ExcelProperty(value = "模型名称",index = 1)
    @ApiModelProperty(name = "algorithmModelName", value = "模型名称", example = "xxx",dataType="String")
    @NotNull(message = "模型名称不能为空")
    private String algorithmModelName;
    

    @ExcelProperty(value = "客户id",index = 1)
    @ApiModelProperty(name = "customerId", value = "客户id", example = "xxx",dataType="Long")
    @NotNull(message = "客户id不能为空")
    private Long customerId;
    

    @ExcelProperty(value = "客户名称",index = 1)
    @ApiModelProperty(name = "customerName", value = "客户名称", example = "xxx",dataType="Long")
    @NotNull(message = "客户名称不能为空")
    private Long customerName;
    

    @ExcelProperty(value = "原始视频基本信息(协议，带宽，图像大小)json",index = 1)
    @ApiModelProperty(name = "videoBaseInfo", value = "原始视频基本信息(协议，带宽，图像大小)json", example = "xxx",dataType="String")
    @NotNull(message = "原始视频基本信息(协议，带宽，图像大小)json不能为空")
    private String videoBaseInfo;
    

    @ExcelProperty(value = "原始视频流播放地址",index = 1)
    @ApiModelProperty(name = "videoPlayUrl", value = "原始视频流播放地址", example = "xxx",dataType="String")
    @NotNull(message = "原始视频流播放地址不能为空")
    private String videoPlayUrl;
    

    @ExcelProperty(value = "实时计算的视频流播放地址",index = 1)
    @ApiModelProperty(name = "computingVideoPlayUrl", value = "实时计算的视频流播放地址", example = "xxx",dataType="String")
    @NotNull(message = "实时计算的视频流播放地址不能为空")
    private String computingVideoPlayUrl;
    

    @ExcelProperty(value = "首次执行时间",index = 1)
    @ApiModelProperty(name = "firstExecTime", value = "首次执行时间", example = "xxx",dataType="Long")
    @NotNull(message = "首次执行时间不能为空")
    private Long firstExecTime;
    

    @ExcelProperty(value = "最近执行时间",index = 1)
    @ApiModelProperty(name = "latestExecTime", value = "最近执行时间", example = "xxx",dataType="Long")
    @NotNull(message = "最近执行时间不能为空")
    private Long latestExecTime;
    

    @ExcelProperty(value = "告警次数",index = 1)
    @ApiModelProperty(name = "alarmAmount", value = "告警次数", example = "xxx",dataType="Integer")
    @NotNull(message = "告警次数不能为空")
    private Integer alarmAmount;
    

    @ExcelProperty(value = "最近告警时间",index = 1)
    @ApiModelProperty(name = "latestAlarmTime", value = "最近告警时间", example = "xxx",dataType="Long")
    @NotNull(message = "最近告警时间不能为空")
    private Long latestAlarmTime;
    

    @ExcelProperty(value = "排序号",index = 1)
    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
    

    @ExcelProperty(value = "任务状态(0=停用,1=启用)",index = 1)
    @ApiModelProperty(name = "taskStatus", value = "任务状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    @NotNull(message = "任务状态(0=停用,1=启用)不能为空")
    private Byte taskStatus;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

}
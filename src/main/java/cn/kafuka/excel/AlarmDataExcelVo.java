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
 * @Description //AlarmDataExcelVo
 * @Date 2023/11/25 12:42
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
public class AlarmDataExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "告警名称",index = 1)
    @ApiModelProperty(name = "name", value = "告警名称", example = "xxx",dataType="String")
    @NotNull(message = "告警名称不能为空")
    private String name;
    

    @ExcelProperty(value = "告警类型(吸烟、打架、人行、人脸、安全帽、口罩、汽车、摩托车、电瓶车)",index = 1)
    @ApiModelProperty(name = "alarmType", value = "告警类型(吸烟、打架、人行、人脸、安全帽、口罩、汽车、摩托车、电瓶车)", example = "xxx",dataType="Long")
    @NotNull(message = "告警类型(吸烟、打架、人行、人脸、安全帽、口罩、汽车、摩托车、电瓶车)不能为空")
    private Long alarmType;
    

    @ExcelProperty(value = "告警图片",index = 1)
    @ApiModelProperty(name = "alarmImage", value = "告警图片", example = "xxx",dataType="String")
    @NotNull(message = "告警图片不能为空")
    private String alarmImage;
    

    @ExcelProperty(value = "告警时间",index = 1)
    @ApiModelProperty(name = "alarmTime", value = "告警时间", example = "xxx",dataType="Long")
    @NotNull(message = "告警时间不能为空")
    private Long alarmTime;
    

    @ExcelProperty(value = "算法任务号",index = 1)
    @ApiModelProperty(name = "taskNo", value = "算法任务号", example = "xxx",dataType="String")
    @NotNull(message = "算法任务号不能为空")
    private String taskNo;
    

    @ExcelProperty(value = "模型序列号",index = 1)
    @ApiModelProperty(name = "modelNo", value = "模型序列号", example = "xxx",dataType="String")
    @NotNull(message = "模型序列号不能为空")
    private String modelNo;
    

    @ExcelProperty(value = "模型名称",index = 1)
    @ApiModelProperty(name = "algorithmModelName", value = "模型名称", example = "xxx",dataType="String")
    @NotNull(message = "模型名称不能为空")
    private String algorithmModelName;
    

    @ExcelProperty(value = "客户号",index = 1)
    @ApiModelProperty(name = "customerNo", value = "客户号", example = "xxx",dataType="String")
    @NotNull(message = "客户号不能为空")
    private String customerNo;
    

    @ExcelProperty(value = "客户名称",index = 1)
    @ApiModelProperty(name = "customerName", value = "客户名称", example = "xxx",dataType="String")
    @NotNull(message = "客户名称不能为空")
    private String customerName;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

}
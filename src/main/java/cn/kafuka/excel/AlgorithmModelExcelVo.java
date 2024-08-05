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
 * @Description //AlgorithmModelExcelVo
 * @Date 2023/11/22 16:06
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
public class AlgorithmModelExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "模型名称",index = 1)
    @ApiModelProperty(name = "name", value = "模型名称", example = "xxx",dataType="String")
    @NotNull(message = "模型名称不能为空")
    private String name;
    

    @ExcelProperty(value = "模型key",index = 1)
    @ApiModelProperty(name = "modelKey", value = "模型key", example = "xxx",dataType="String")
    @NotNull(message = "模型key不能为空")
    private String modelKey;
    

    @ExcelProperty(value = "模型序列号",index = 1)
    @ApiModelProperty(name = "modelSerial", value = "模型序列号", example = "xxx",dataType="String")
    @NotNull(message = "模型序列号不能为空")
    private String modelSerial;
    

    @ExcelProperty(value = "算法类型",index = 1)
    @ApiModelProperty(name = "algorithmType", value = "算法类型", example = "xxx",dataType="Long")
    @NotNull(message = "算法类型不能为空")
    private Long algorithmType;
    

    @ExcelProperty(value = "核心技术",index = 1)
    @ApiModelProperty(name = "coreTech", value = "核心技术", example = "xxx",dataType="String")
    @NotNull(message = "核心技术不能为空")
    private String coreTech;
    

    @ExcelProperty(value = "最近训练时间",index = 1)
    @ApiModelProperty(name = "latestTrainingTime", value = "最近训练时间", example = "xxx",dataType="Long")
    @NotNull(message = "最近训练时间不能为空")
    private Long latestTrainingTime;
    

    @ExcelProperty(value = "上线时间",index = 1)
    @ApiModelProperty(name = "onlineTime", value = "上线时间", example = "xxx",dataType="Long")
    @NotNull(message = "上线时间不能为空")
    private Long onlineTime;
    

    @ExcelProperty(value = "标签列表",index = 1)
    @ApiModelProperty(name = "labelList", value = "标签列表", example = "xxx",dataType="String")
    @NotNull(message = "标签列表不能为空")
    private String labelList;
    

    @ExcelProperty(value = "oos访问地址",index = 1)
    @ApiModelProperty(name = "oosUrl", value = "oos访问地址", example = "xxx",dataType="String")
    @NotNull(message = "oos访问地址不能为空")
    private String oosUrl;
    

    @ExcelProperty(value = "排序号",index = 1)
    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
    

    @ExcelProperty(value = "状态",index = 1)
    @ApiModelProperty(name = "status", value = "状态", example = "xxx",dataType="Byte")
    @NotNull(message = "状态不能为空")
    private Byte status;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

}
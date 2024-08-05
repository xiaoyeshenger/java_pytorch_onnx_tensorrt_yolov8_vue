package cn.kafuka.bo.dto;


import cn.kafuka.bo.dto.BaseDto;
import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * @Author zhangyong
 * @Description //AlgorithmModelReqDto
 * @Date 2023/11/22 16:06
 * @Param
 * @return
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmModelReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;


    @ApiModelProperty(name = "name", value = "模型名称", example = "xxx",dataType="String")
    @NotNull(message = "模型名称不能为空")
    private String name;
    

    @ApiModelProperty(name = "modelKey", value = "模型key", example = "xxx",dataType="String")
    @NotNull(message = "模型key不能为空")
    private String modelKey;
    

    @ApiModelProperty(name = "algorithmType", value = "算法类型", example = "xxx",dataType="Long")
    @NotNull(message = "算法类型不能为空")
    private Long algorithmType;
    

    @ApiModelProperty(name = "coreTech", value = "核心技术", example = "xxx",dataType="String")
    @NotNull(message = "核心技术不能为空")
    private String coreTech;
    

    @ApiModelProperty(name = "latestTrainingTime", value = "最近训练时间", example = "xxx",dataType="Long")
    @NotNull(message = "最近训练时间不能为空")
    private Long latestTrainingTime;
    

    @ApiModelProperty(name = "onlineTime", value = "上线时间", example = "xxx",dataType="Long")
    @NotNull(message = "上线时间不能为空")
    private Long onlineTime;
    

    @ApiModelProperty(name = "labelList", value = "标签列表", example = "xxx",dataType="String")
    @NotNull(message = "标签列表不能为空")
    private String labelList;


    @ApiModelProperty(name = "shellKey", value = "模型调用关键字", example = "yolo",dataType="String")
    @NotNull(message = "模型调用关键字不能为空")
    private String shellKey;

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    @NotNull(message = "排序号不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Integer orderNum;

    @ApiModelProperty(value = "状态")
    @NotNull(message = "状态不能为空",groups = ValidationGroup.ValidationUpdate.class)
    @Min(value = 0,message = "状态只能为0或1")
    @Max(value = 1,message = "状态只能为0或1")
    private Byte status;

}
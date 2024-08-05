package cn.kafuka.bo.dto;


import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class AlgorithmTaskStatusReqDto extends BaseDto {

    @ApiModelProperty(name = "taskNo", value = "任务号", example = "xxx",dataType="String")
    @NotNull(message = "任务号不能为空")
    private String taskNo;

    @ApiModelProperty(value = "任务状态")
    @NotNull(message = "任务状态不能为空")
    @Min(value = 0,message = "任务状态只能为0或1")
    @Max(value = 1,message = "任务状态只能为0或1")
    private Byte taskStatus;

}
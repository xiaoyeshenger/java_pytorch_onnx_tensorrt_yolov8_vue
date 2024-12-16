package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/**
 * @Author zhangyong
 * @Description //AlgorithmTaskReqDto
 * @Date 2023/11/23 14:06
 * @Param
 * @return
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmTaskDeleteReqDto extends BaseDto {

    @ApiModelProperty(name = "taskNo", value = "任务号", example = "xxx",dataType="String")
    @NotNull(message = "任务号不能为空")
    private String taskNo;

}
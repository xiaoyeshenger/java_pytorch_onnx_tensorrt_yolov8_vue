package cn.kafuka.bo.dto;


import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/**
 * @Author zhangyong
 * @Description //AlgorithmInferResultReqDto
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
public class AlgorithmInferResultReqDto extends BaseDto {

    @ApiModelProperty(name = "task_no", value = "算法任务号", example = "xxx",dataType="String")
    @NotNull(message = "算法任务号不能为空")
    private String task_no;

    @ApiModelProperty(name = "model_name", value = "模型名称", example = "xxx",dataType="String")
    @NotNull(message = "模型名称不能为空")
    private String model_name;

    @ApiModelProperty(name = "img_file_name", value = "minio文件名称", example = "xxx",dataType="String")
    @NotNull(message = "minio文件名称不能为空")
    private String img_file_name;

    @ApiModelProperty(name = "bucket_name", value = "minio桶名称", example = "xxx",dataType="String")
    @NotNull(message = "minio桶名称不能为空")
    private String bucket_name;

    @ApiModelProperty(name = "cls_score", value = "分类得分", example = "xxx",dataType="String")
    @NotNull(message = "分类得分不能为空")
    private String cls_score;

    @ApiModelProperty(name = "alarm_time", value = "告警时间", example = "xxx",dataType="String")
    @NotNull(message = "告警时间不能为空")
    private String alarm_time;
}
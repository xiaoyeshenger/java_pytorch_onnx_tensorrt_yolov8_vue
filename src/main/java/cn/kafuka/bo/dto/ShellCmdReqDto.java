package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;


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
public class ShellCmdReqDto extends BaseDto {


    @ApiModelProperty(name = "workDir", value = "工作目录", example = "/data/app/yolo",dataType="String")
    @NotNull(message = "工作目录不能为空")
    private String workDir;

    @ApiModelProperty(name = "cmdList", value = "cmd/shell命令列表", example = "ls /data/",dataType="String")
    @NotNull(message = "cmd/shell命令列表不能为空")
    private List<String> cmdList;
}
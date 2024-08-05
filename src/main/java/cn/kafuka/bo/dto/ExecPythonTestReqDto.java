package cn.kafuka.bo.dto;


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
public class ExecPythonTestReqDto extends BaseDto {



    @ApiModelProperty(name = "filePath", value = "python文件地址", example = "xxx",dataType="String")
    private String filePath;

    @ApiModelProperty(name = "cmdPrefix", value = "cmd前缀", example = "sh",dataType="String")
    private String cmdPrefix;

    @ApiModelProperty(name = "shellCmd", value = "shell命令", example = "xxx",dataType="String")
    private String shellCmd;
}
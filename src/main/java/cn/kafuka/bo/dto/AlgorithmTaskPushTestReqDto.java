package cn.kafuka.bo.dto;


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
public class AlgorithmTaskPushTestReqDto extends BaseDto {

    @ApiModelProperty(name = "serialNum", value = "设备序列号", example = "xxx",dataType="String")
    @NotNull(message = "设备序列号不能为空")
    private String serialNum;

    @ApiModelProperty(name = "videoUrl", value = "视频地址", example = "xxx",dataType="String")
    @NotNull(message = "视频地址不能为空")
    private String videoUrl;

    @ApiModelProperty(name = "pushStreamServerUrl", value = "流媒体地址", example = "xxx",dataType="String")
    @NotNull(message = "流媒体地址不能为空")
    private String pushStreamServerUrl;

    @ApiModelProperty(name = "pushStreamServerSign", value = "流媒体sign", example = "xxx",dataType="String")
    @NotNull(message = "流媒体sign不能为空")
    private String pushStreamServerSign;

    private String modelPath;
}
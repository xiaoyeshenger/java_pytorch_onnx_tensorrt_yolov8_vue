package cn.kafuka.bo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class HttpPushLogReqDto {

    @ApiModelProperty(name = "httpPushLogId", value = "httpPushLogId", example = "56asf2454454545",dataType="String")
    @NotNull(message = "httpPushLogId不能为空")
    private String httpPushLogId;
}

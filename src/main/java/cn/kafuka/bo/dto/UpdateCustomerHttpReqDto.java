package cn.kafuka.bo.dto;


import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * @Author zhangyong
 * @Description //UpdateCustomerHttpReqDto
 * @Date xxx 17:23
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerHttpReqDto extends BaseDto {

    @ApiModelProperty(name = "customerNo", value = "客户序列号", example = "gk8v6h4l",dataType="String")
    @NotNull(message = "客户序列号不能为空")
    private String customerNo;

    @ApiModelProperty(name = "httpReqUrl", value = "http推送地址", example = "xxx",dataType="String")
    @NotNull(message = "http推送地址不能为空")
    private String httpReqUrl;

    @ApiModelProperty(name = "httpReqHeader", value = "http请求头json字符串", example = "xxx",dataType="String")
    private String httpReqHeader;


}
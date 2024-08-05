package cn.kafuka.bo.dto;


import cn.kafuka.bo.dto.BaseDto;
import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //CustomerReqDto
 * @Date 2023/11/20 17:23
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(name = "mobileNum", value = "手机号", example = "xxx",dataType="String")
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$",
            message = "手机号码格式不对")
    private String mobileNum;

    @ApiModelProperty(name = "taskAmountLimit", value = "任务数量限制(允许该客户最多叠加多少路算法)", example = "xxx",dataType="Integer")
    private Integer taskAmountLimit;

    @ApiModelProperty(name = "httpReqUrl", value = "http推送地址", example = "xxx",dataType="String")
    private String httpReqUrl;

    @ApiModelProperty(name = "httpReqHeader", value = "http请求头json字符串", example = "xxx",dataType="String")
    private String httpReqHeader;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    private Byte status;

}
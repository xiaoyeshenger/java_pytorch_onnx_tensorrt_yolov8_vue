package cn.kafuka.bo.dto;


import cn.kafuka.bo.dto.PageReqDto;
import cn.kafuka.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * @Author zhangyong
 * @Description //AlgorithmTaskPageReqDto
 * @Date 2023/11/23 14:06
 * @Param
 * @return
 */
@Getter
@Setter
@Accessors(chain=true)
public class AlgorithmTaskPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "status", value = "任务状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    private Byte status;

    @ApiModelProperty(name = "taskNo", value = "任务号(ddgjehf)", example = "xxx",dataType="String")
    private String taskNo;

    @ApiModelProperty(name = "modelNo", value = "模型号(gknjxbgh)", example = "xxx",dataType="String")
    private String modelNo;

    @ApiModelProperty(name = "customNo", value = "客户号(gknjxbgh)", example = "xxx",dataType="String")
    private String customNo;

}
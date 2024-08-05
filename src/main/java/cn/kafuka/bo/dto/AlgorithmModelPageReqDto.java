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
 * @Description //AlgorithmModelPageReqDto
 * @Date 2023/11/22 16:06
 * @Param
 * @return
 */
@Getter
@Setter
@Accessors(chain=true)
public class AlgorithmModelPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "algorithmType", value = "算法类型", example = "xxx",dataType="Long")
    private Long algorithmType;

    @ApiModelProperty(name = "status", value = "状态", example = "xxx",dataType="Byte")
    private Byte status;

    @ApiModelProperty(name = "modelNoList", value = "模型序列号列表", example = "xxx",dataType="List")
    private List<String> modelNoList;

}
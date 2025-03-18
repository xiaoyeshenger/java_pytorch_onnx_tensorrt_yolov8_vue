package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * @Author zhangyong
 * @Description //AlgorithmModelListReqDto
 * @Date 2023/11/22 16:06
 * @Param
 * @return
 */
@Getter
@Setter
@Accessors(chain=true)
public class AlgorithmModelListReqDto extends BaseDto {

    @ApiModelProperty(name = "limit", value = "最大条数", example = "xxx",dataType="Byte")
    private Integer limit;

}
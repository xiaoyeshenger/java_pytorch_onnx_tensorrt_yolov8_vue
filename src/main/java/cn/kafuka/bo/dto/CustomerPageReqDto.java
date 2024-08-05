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


/*
 * @Author zhangyong
 * @Description //CustomerPageReqDto
 * @Date 2023/11/20 17:23
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class CustomerPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    private Byte status;

}
package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/*
 * @Author 张勇
 * @Description //UserReqDto
 * @Date 2021/05/21 18:43
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserRegTypeReqDto extends BaseDto {

    @ApiModelProperty(name = "userId ", value = "用户ID ", example = "1235648456515",dataType="Long")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty(name = "regType", value = "用户注册类型(正式/申请中/未通过审核)", example = "8",dataType="Long")
    @NotNull(message = "用户注册类型不能为空")
    @Min(value = 8,message = "用户注册类型只能为8(申请通过)或9(未通过审核)")
    @Max(value = 9,message = "用户注册类型只能为8(申请通过)或9(未通过审核)")
    private Long regType;

    @ApiModelProperty(name = "remark", value = "审核不通过的文字", example = "由于地图经纬度不正确,请重新修改后提交",dataType="String")
    private String remark;
}
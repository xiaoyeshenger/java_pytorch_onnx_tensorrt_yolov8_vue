package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @Author zhangyong
 * @Description //OperateLogPageReqDto
 * @Date xxx 15:39
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class HttpPushLogPageReqDto extends PageReqDto {


    @ApiModelProperty(name = "customerNo", value = "客户号", example = "gk8v6h4l",dataType="String")
    @NotNull(message = "客户序列号不能为空")
    private String customerNo;


    @ApiModelProperty(name = "taskNo", value = "任务号", example = "njv57e9n",dataType="String")
    private String taskNo;


    @ApiModelProperty(name = "modelNo", value = "模型序列号", example = "ad45gfbh",dataType="String")
    private String modelNo;


    @ApiModelProperty(name = "pushType", value = "推送类型(详见字典)", example = "911",dataType="Long")
    private Long pushType;

    @ApiModelProperty(name = "status", value = "推送状态(0=失败,1=成功)", example = "xxx",dataType="Byte")
    @Min(value = 0,message = "推送状态只能为0或1")
    @Max(value = 1,message = "推送状态只能为0或1")
    private Byte status;


    @ApiModelProperty(name = "latestData", value = "是否是最新数据(0=否,1=是)", example = "xxx",dataType="Byte")
    @Min(value = 0,message = "是否是最新数据只能为0或1")
    @Max(value = 1,message = "是否是最新数据只能为0或1")
    private Byte latestData;


    @ApiModelProperty(name = "startTime", value = "推送开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "推送结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}
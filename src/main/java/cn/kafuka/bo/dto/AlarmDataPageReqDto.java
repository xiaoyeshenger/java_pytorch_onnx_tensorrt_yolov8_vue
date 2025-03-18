package cn.kafuka.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;



/**
 * @Author zhangyong
 * @Description //AlarmDataPageReqDto
 * @Date 2023/11/25 12:42
 * @Param
 * @return
 */
@Getter
@Setter
@Accessors(chain=true)
public class AlarmDataPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "alarmType", value = "告警类型(吸烟、打架、人行、人脸、安全帽、口罩、汽车、摩托车、电瓶车)", example = "xxx",dataType="Long")
    private Long alarmType;

    @ApiModelProperty(name = "taskNo", value = "算法任务号", example = "xxx",dataType="String")
    private String taskNo;

    @ApiModelProperty(name = "modelNo", value = "算法模型号", example = "xxx",dataType="String")
    private String modelNo;

    @ApiModelProperty(name = "customerNo", value = "客户号", example = "xxx",dataType="String")
    private String customerNo;

    @ApiModelProperty(name = "startTime", value = "告警时间开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "告警时间结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}
package cn.kafuka.bo.po;


import cn.kafuka.mongo.MongoIdEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Builder
@Accessors(chain=true)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "AlarmData")
public class AlarmData extends MongoIdEntity {

    @ApiModelProperty(value = "告警名称")
    private String name;

    @ApiModelProperty(value = "告警类型(吸烟、打架、人行、人脸、安全帽、口罩、汽车、摩托车、电瓶车)")
    private Long alarmType;

    @ApiModelProperty(value = "告警图片")
    private String alarmImage;

    @ApiModelProperty(value = "告警时间")
    private Long alarmTime;

    @ApiModelProperty(value = "告警时间字符串")
    private String alarmDate;

    @ApiModelProperty(value = "任务号")
    private String taskNo;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "模型序列号")
    private String modelNo;

    @ApiModelProperty(value = "模型名称")
    private String modelName;

    @ApiModelProperty(value = "客户号")
    private String customerNo;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "原始视频播放地址")
    private String videoPlayUrl;

    @ApiModelProperty(value = "流媒体服务器地址")
    private String streamServerUrl;

    @ApiModelProperty(value = "实时计算视频播放地址")
    private String computingVideoPlayUrl;

    @ApiModelProperty(value = "实时推送视频播放地址")
    private String pushVideoPlayUrl;

    @ApiModelProperty(value = "记录生成时间")
    private Date createAt;

}
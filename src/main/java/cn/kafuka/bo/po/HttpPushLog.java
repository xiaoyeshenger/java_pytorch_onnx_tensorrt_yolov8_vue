package cn.kafuka.bo.po;


import cn.kafuka.mongo.MongoIdEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

//http推送日志
@Getter
@Setter
@Builder
@Accessors(chain=true)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "HttpPushLog")
public class HttpPushLog extends MongoIdEntity {


    @ApiModelProperty(value = "推送时间")
    private Long pushTime;

    @ApiModelProperty(value = "推送时间字符串")
    private String pushDate;

    @ApiModelProperty(value = "推送状态(0=失败,1=成功)")
    private Byte status;

    @ApiModelProperty(value = "是否是最新数据(0=不是,1=是)")
    private Byte latestData;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "http请求地址")
    private String httpReqUrl;

    @ApiModelProperty(value = "http请求头")
    private String httpReqHeader;

    @ApiModelProperty(value = "http请求参数")
    private String httpReqParam;

    @ApiModelProperty(value = "http返回结果")
    private String httpResult;

    @ApiModelProperty(value = "关键字(比如ID)")
    private String keyWord;

    @ApiModelProperty(value = "原始参数")
    private String oriParam;

    @ApiModelProperty(value = "错误信息")
    private String errorMsg;

    @ApiModelProperty(value = "任务号")
    private String taskNo;

    @ApiModelProperty(value = "任务号")
    private String taskName;

    @ApiModelProperty(value = "模型序列号")
    private String modelNo;

    @ApiModelProperty(value = "模型名称")
    private String modelName;

    @ApiModelProperty(value = "客户号")
    private String customerNo;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "记录生成时间")
    private Date createAt;

}
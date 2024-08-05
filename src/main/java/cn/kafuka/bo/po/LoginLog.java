package cn.kafuka.bo.po;

import cn.kafuka.mongo.MongoIdEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

//登录日志
@Getter
@Setter
@Builder
@Accessors(chain=true)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "LoginLog")
public class LoginLog extends MongoIdEntity {


    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "IP地址")
    private String ip;

    @ApiModelProperty(value = "登录地址")
    private String location;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "提示消息")
    private String msg;

    @ApiModelProperty(value = "操作系统")
    private String os;

    @ApiModelProperty(value = "登录状态(0=成功,1=失败)")
    private Byte status;

    @ApiModelProperty(value = "园区ID")
    private Long parkId;

    @ApiModelProperty(value = "登录时间")
    private Long loginTime;

    @ApiModelProperty(value = "登录时间字符串")
    private String loginDate;


}
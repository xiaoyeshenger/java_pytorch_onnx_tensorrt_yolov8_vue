package cn.kafuka.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;


@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobileNum;

    @ApiModelProperty(value = "客户号")
    private String customerNo;

    @ApiModelProperty(value = "访问key")
    private String accessKey;

    @ApiModelProperty(value = "http推送地址")
    private String httpReqUrl;

    @ApiModelProperty(value = "http请求头json字符串")
    private String httpReqHeader;

    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间")
    private Long loginTime;

    @ApiModelProperty(value = "任务数量限制(允许该客户最多叠加多少路算法)")
    private Integer taskAmountLimit;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}
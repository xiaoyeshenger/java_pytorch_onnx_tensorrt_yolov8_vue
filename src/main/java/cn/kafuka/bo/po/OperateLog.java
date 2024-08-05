package cn.kafuka.bo.po;

import cn.kafuka.mongo.MongoIdEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

//操作日志
@Getter
@Setter
@Builder
@Accessors(chain=true)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "OperateLog")
public class OperateLog extends MongoIdEntity {


    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    //(0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据)
    @ApiModelProperty(value = "业务类型")
    private Long businessType;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "方法中文名称")
    private String methodCnName;

    @ApiModelProperty(value = "请求方法")
    private String requestMethod;

    @ApiModelProperty(value = "请求参数")
    private String reqParam;

    @ApiModelProperty(value = "返回结果")
    private String respResult;

    @ApiModelProperty(value = "操作类型(0=其它,1=后台用户,2=手机端用户)")
    private Long type;

    @ApiModelProperty(value = "关键字(比如cid)")
    private String keyWord;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "操作者IP")
    private String ip;

    @ApiModelProperty(value = "操作者地点(内网IP/外网IP)")
    private String location;

    @ApiModelProperty(value = "操作状态(0=失败,1=成功)")
    private Byte status;

    @ApiModelProperty(value = "操作时间")
    private Long operateTime;

    @ApiModelProperty(value = "操作时间字符串")
    private String operateDate;

    @ApiModelProperty(value = "错误信息")
    private String errorMsg;

    @ApiModelProperty(value = "操作者姓名")
    private String operatorName;

    @ApiModelProperty(value = "创建者电话")
    private String operatorMobile;

}
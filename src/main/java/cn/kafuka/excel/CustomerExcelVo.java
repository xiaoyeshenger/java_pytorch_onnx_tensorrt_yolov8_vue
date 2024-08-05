package cn.kafuka.excel;


import cn.kafuka.bo.dto.BaseDto;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.annotation.FieldDupValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //CustomerExcelVo
 * @Date 2023/11/20 17:23
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "名称",index = 1)
    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
    

    @ExcelProperty(value = "手机号",index = 1)
    @ApiModelProperty(name = "mobileNum", value = "手机号", example = "xxx",dataType="String")
    @NotNull(message = "手机号不能为空")
    private String mobileNum;
    

    @ExcelProperty(value = "访问key",index = 1)
    @ApiModelProperty(name = "accessKey", value = "访问key", example = "xxx",dataType="String")
    @NotNull(message = "访问key不能为空")
    private String accessKey;
    

    @ExcelProperty(value = "http推送地址",index = 1)
    @ApiModelProperty(name = "httpReqUrl", value = "http推送地址", example = "xxx",dataType="String")
    @NotNull(message = "http推送地址不能为空")
    private String httpReqUrl;
    

    @ExcelProperty(value = "http请求头json字符串",index = 1)
    @ApiModelProperty(name = "httpReqHeader", value = "http请求头json字符串", example = "xxx",dataType="String")
    @NotNull(message = "http请求头json字符串不能为空")
    private String httpReqHeader;
    

    @ExcelProperty(value = "最后登录IP",index = 1)
    @ApiModelProperty(name = "loginIp", value = "最后登录IP", example = "xxx",dataType="String")
    @NotNull(message = "最后登录IP不能为空")
    private String loginIp;
    

    @ExcelProperty(value = "最后登录时间",index = 1)
    @ApiModelProperty(name = "loginTime", value = "最后登录时间", example = "xxx",dataType="Long")
    @NotNull(message = "最后登录时间不能为空")
    private Long loginTime;
    

    @ExcelProperty(value = "任务数量限制(允许该客户最多叠加多少路算法)",index = 1)
    @ApiModelProperty(name = "taskAmountLimit", value = "任务数量限制(允许该客户最多叠加多少路算法)", example = "xxx",dataType="Integer")
    @NotNull(message = "任务数量限制(允许该客户最多叠加多少路算法)不能为空")
    private Integer taskAmountLimit;
    

    @ExcelProperty(value = "状态(0=停用,1=启用)",index = 1)
    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    @NotNull(message = "状态(0=停用,1=启用)不能为空")
    private Byte status;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

}
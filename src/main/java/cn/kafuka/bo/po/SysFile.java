package cn.kafuka.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//系统文件
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class SysFile implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "文件类型(图标/excel导入模板/word导入模板/pdf导入模板/音频/视频)")
    private Long type;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件键名称")
    private String fileKey;

    @ApiModelProperty(value = "访问路径")
    private String accessPath;

    @ApiModelProperty(value = "存储路径(桶名称)")
    private String storePath;

    @ApiModelProperty(value = "源文件名称")
    private String oriFileName;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "激活标志(0=未激活,1=已激活)")
    private Byte activeFlag;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}
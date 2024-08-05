package cn.kafuka.bo.po;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//权限(目录,菜单,按钮)
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private Long id;

    //中文名称
    private String name;

    //权限字符串如 sys:create ，对应shiro权限注解
    private String perms;

    //跳转路径(路由地址)
    private String path;

    //路由参数
    private String query;

    //url
    private String url;

    //组件路径
    private String component;

    //中文名称
    private String routerName;

    //图标
    private String icon;

    //父级id
    private Long parentId;

    //父级名称
    private String parentName;

    //排序
    private Integer orderNum;

    //权限类型(目录=Catalog,菜单=Menu,按钮=Button)
    private Long permsType;

    //使用类型(PC/APP)
    private Long useType;

    //链接类型(内部接口/外部链接)(inner/out/web/list)
    private Long linkType ;

    //状态
    private Byte status;

    //创建时间
    private Long createTime;

}
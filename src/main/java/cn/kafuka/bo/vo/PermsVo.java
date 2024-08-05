package cn.kafuka.bo.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class PermsVo extends BaseVo {

    //id
    private Long id;

    //父级id
    private Long parentId;

    //路由名字
    private String name;

    //权限类型(目录/菜单/按钮)
    private Long permsType;

    //权限字符
    private String perms;

    //路由地址
    private String path;

    //icon
    private String icon;

    //url
    private String url;


    //是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
    private boolean hidden;

    //重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
    private String redirect;

    //组件地址
    private String component;

    //路由参数：如 {"id": 1, "name": "ry"}
    private String query;

    //当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
    private Boolean alwaysShow;

    //其他元素
    private MetaVo meta;

    //排序
    private Integer orderNum;

    //子路由
    private List<PermsVo> children;
}

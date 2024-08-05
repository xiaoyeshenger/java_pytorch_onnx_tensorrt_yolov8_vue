package cn.kafuka.bo.vo;

import lombok.*;
import lombok.experimental.Accessors;

//路由显示信息
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class MetaVo extends BaseVo {

    //设置该路由在侧边栏和面包屑中展示的名字
    private String title;

    //设置该路由的图标，对应路径src/assets/icons/svg
    private String icon;

    //设置为true，则不会被 <keep-alive>缓存
    private boolean noCache;

    //内链地址（http(s)://开头）
    private String link;
}

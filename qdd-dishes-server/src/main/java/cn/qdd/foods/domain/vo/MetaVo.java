package cn.qdd.foods.domain.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 路由显示信息
 *
 * @author 东方曜
 */
@Data
@NoArgsConstructor
public class MetaVo {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;


    @Builder
    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }


}

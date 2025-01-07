package cn.qdd.foods.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 菜单权限表 sys_menu
 *
 * @author 东方曜
 */
@Data
@TableName("sys_menu")
@NoArgsConstructor
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private String orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;


    /**
     * 类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    /**
     * 显示状态（1显示 -1隐藏）
     */
    private String visible;

    /**
     * 菜单状态（1显示 -1隐藏）
     */
    private Integer status;

    /**
     * 权限字符串
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> children;

    public List<SysMenu> getChildren() {

        if (Objects.isNull(children)) {
            return new ArrayList<>();
        }

        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    @Builder
    public SysMenu(Long menuId, String menuName, String parentName, Long parentId, String orderNum, String path, String component, String menuType, String visible, Integer status, String perms, String icon, String createBy, Date createTime, String updateBy, Date updateTime, List<SysMenu> children) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.parentName = parentName;
        this.parentId = parentId;
        this.orderNum = orderNum;
        this.path = path;
        this.component = component;
        this.menuType = menuType;
        this.visible = visible;
        this.status = status;
        this.perms = perms;
        this.icon = icon;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.children = children;
    }


}

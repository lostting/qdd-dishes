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
import java.util.Date;

/**
 * 角色表 sys_role
 *
 * @author 东方曜
 */
@Data
@NoArgsConstructor
@TableName("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;



    /**
     * 角色状态（1正常 -1停用）
     */
    private Integer status;




    /**
     * 菜单组
     */
    @TableField(exist = false)
    private Long[] menuIds;


    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


    @Builder
    public SysRole(Long roleId, String roleName, String roleKey, Integer status, Long[] menuIds, Date createTime, Date updateTime) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.status = status;
        this.menuIds = menuIds;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}

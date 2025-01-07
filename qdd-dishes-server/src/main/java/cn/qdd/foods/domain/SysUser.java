package cn.qdd.foods.domain;


import cn.qdd.foods.domain.vo.RouterVo;
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
import java.util.List;

/**
 * @description sys_user
 * @author 东方曜
 * @date 2022-11-02
 */
@Data
@NoArgsConstructor
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 最后登录ip
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /*
     *  token
     */
    @TableField(exist = false)
    private String token;
    /**
     * 权限id
     */
    @TableField(exist = false)
    private Long roleId;
    /**
     * 权限key
     */
    @TableField(exist = false)
    private String roleKey;
    /**
     * 权限name
     */
    @TableField(exist = false)
    private String roleName;


    /**
     * 路由对象
     */
    @TableField(exist = false)
    private List<RouterVo> menuList;

@Builder
    public SysUser(Integer userId, String userName, String phoneNumber, String avatar, String password, Integer status, String loginIp, Date loginDate, Date createTime, Date updateTime, String remark, String token, Long roleId, String roleKey, String roleName, List<RouterVo> menuList) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.password = password;
        this.status = status;
        this.loginIp = loginIp;
        this.loginDate = loginDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.remark = remark;
        this.token = token;
        this.roleId = roleId;
        this.roleKey = roleKey;
        this.roleName = roleName;
        this.menuList = menuList;
    }
}
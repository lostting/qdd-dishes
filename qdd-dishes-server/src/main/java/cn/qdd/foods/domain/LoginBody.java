package cn.qdd.foods.domain;

import lombok.Data;

/**
 * 用户登录对象
 * 
 * @author 东方曜
 *
 */
@Data
public class LoginBody
{
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;


    /**
     * 唯一标识
     */
    private String uuid ;

}

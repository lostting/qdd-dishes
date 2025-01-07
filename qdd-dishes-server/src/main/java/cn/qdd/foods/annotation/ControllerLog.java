package cn.qdd.foods.annotation;


import cn.qdd.foods.enums.BusinessType;

import java.lang.annotation.*;


/**
 * 自定义操作日志记录注解
 *
 * @author 东方曜
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;


}

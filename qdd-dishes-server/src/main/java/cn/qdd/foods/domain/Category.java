package cn.qdd.foods.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @description album
 * @author 东方曜
 * @date
 */
@Data
@TableName("categories")
@NoArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    private String createdAt;
    private String updatedAt;
}
package cn.qdd.foods.mapper;

import cn.qdd.foods.domain.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    // 根据分类 ID 获取对应的菜品
    @Select("SELECT * FROM dishes WHERE category_id = #{categoryId}")
    List<Dish> getDishesByCategoryId(Long categoryId);

    @Update("UPDATE dishes SET count = count + 1 WHERE id = #{dishId}")
    void updateDishCountById(@Param("dishId") Integer dishId);
}

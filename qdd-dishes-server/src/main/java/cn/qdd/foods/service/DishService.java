package cn.qdd.foods.service;




import cn.qdd.foods.domain.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 东方曜
 * @date 2020/12/14 11:01
 */
public interface DishService  extends IService<Dish> {
    // 根据分类 ID 获取菜品
    List<Dish> getDishesByCategoryId(Long categoryId);

    void addDishCount(Integer dishId);

    // 可以添加更多自定义业务方法
}

package cn.qdd.foods.controller;

import cn.qdd.foods.domain.Category;
import cn.qdd.foods.domain.Dish;
import cn.qdd.foods.domain.base.Result;
import cn.qdd.foods.domain.base.ResultUtil;
import cn.qdd.foods.service.CategoryService;
import cn.qdd.foods.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 东方曜

 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
@Api(tags = "菜品管理接口")
public class CategoryController {

    private final DishService dishService;
    private final CategoryService categoryService;


    // 根据分类 ID 获取菜品
    @GetMapping("/list/{categoryId}")
    @ApiOperation(value = "根据分类 ID 获取菜品")
    public Result getDishesByCategoryId(@PathVariable Long categoryId) {
        List<Dish> dishes = dishService.getDishesByCategoryId(categoryId);
        return ResultUtil.success(dishes);
    }  // 根据分类 ID 获取菜品
    @GetMapping("/list")
    @ApiOperation(value = "根据分类 ID 获取菜品")
    public Result getDishes() {
        List<Category> categories = categoryService.list();
        List<Category> result = new ArrayList<>();
        for (Category category : categories) {
            List<Dish> dishes = dishService.getDishesByCategoryId(Long.valueOf(category.getId()));
            if (!dishes.isEmpty()) {
                result.add(category);
            }

        }
        return ResultUtil.success(result);
    }

    // 保存菜品
    @PostMapping("/save")
    @ApiOperation(value = "保存菜品")
    public Result saveDish(@RequestBody Dish dish) {
        dishService.save(dish);
        return ResultUtil.success();
    }
}

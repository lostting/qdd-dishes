package cn.qdd.foods.controller;

import cn.qdd.foods.domain.Dish;
import cn.qdd.foods.domain.base.Result;
import cn.qdd.foods.domain.base.ResultUtil;
import cn.qdd.foods.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 东方曜
 * @date 2022/11/01 14:35
 */
@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;


    // 根据分类 ID 获取菜品
    @GetMapping("/list/{categoryId}")
    @ApiOperation(value = "根据分类 ID 获取菜品")
    public Result getDishesByCategoryId(@PathVariable Long categoryId) {
        List<Dish> dishes = dishService.getDishesByCategoryId(categoryId);
        return ResultUtil.success(dishes);
    }

    // 保存菜品
    @PostMapping("/save")
    @ApiOperation(value = "保存菜品")
    public Result saveDish(@RequestBody Dish dish) {
        dishService.save(dish);
        return ResultUtil.success();
    }
    // 保存菜品
    @GetMapping("/addCount/{dishId}")
    @ApiOperation(value = "保存菜品")
    public Result addDishCount(@PathVariable Integer dishId) {
        dishService.addDishCount(dishId);
        return ResultUtil.success();
    }

}

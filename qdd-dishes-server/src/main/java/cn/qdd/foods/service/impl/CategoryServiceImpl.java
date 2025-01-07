package cn.qdd.foods.service.impl;


import cn.qdd.foods.domain.Category;
import cn.qdd.foods.domain.Dish;
import cn.qdd.foods.mapper.CategoryMapper;
import cn.qdd.foods.mapper.DishMapper;
import cn.qdd.foods.service.CategoryService;
import cn.qdd.foods.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 东方曜
 * @date 2022/11/01 14:37
 */
@Slf4j
@Service
@RequiredArgsConstructor
class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


}

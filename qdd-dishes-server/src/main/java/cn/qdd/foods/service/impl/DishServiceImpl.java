package cn.qdd.foods.service.impl;


import cn.qdd.foods.domain.Dish;
import cn.qdd.foods.mapper.DishMapper;
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
class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {


    @Resource
    private DishMapper dishMapper;




    @Override
    public List<Dish> getDishesByCategoryId(Long categoryId) {
//       String path= "http://192.168.124.3:91/img/";
       String path= "http://127.0.0.1:91/img/";
        List<Dish> list;
        if (categoryId==0) {
            list = dishMapper.selectList(null);
        }else {
            list= dishMapper.getDishesByCategoryId(categoryId);
        }
        for (Dish dish : list) {
            dish.setImageUrl(path+dish.getImageUrl());
        }
        return list;
    }

    @Override
    public void addDishCount(Integer dishId) {
        dishMapper.updateDishCountById(dishId);
    }
}

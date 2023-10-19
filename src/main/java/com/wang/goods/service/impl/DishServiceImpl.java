package com.wang.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.goods.common.DefinedCorrelationException;
import com.wang.goods.dto.DishDTO;
import com.wang.goods.mapper.DishMapper;
import com.wang.goods.pojo.Dish;
import com.wang.goods.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {


//    @Autowired
//    DishFlavorService dishFlavorService;
    @Autowired
    DishService dishService;
    /**
     * 根据菜品ID查找对应的一个或者多个口味，为DTO对象设置口味列表属性flavors，并返回DTO对象
     * @param id
     * @return
     */
//    @Override
//    public DishDTO getByIdWithFlavor(Long id) {
//
//        DishDTO dishDTO = new DishDTO();
//        //根据id得到菜品 复制到 dishDTO
//        Dish dish = this.getById(id);
//        BeanUtils.copyProperties(dish, dishDTO);
//        //根据菜品id查询口味
//        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, id);
//        List<DishFlavor> dishFlavorList = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
//        //口味赋值到dishDto
//        dishDTO.setFlavors(dishFlavorList);
//
//        return dishDTO;
//    }

//    @Override
//    @Transactional
//    public void saveWithFlavor(DishDTO dishDTO) {
//
//        this.save(dishDTO);
//        Long dishId = dishDTO.getId();
//        List<DishFlavor> flavors = dishDTO.getFlavors();
//        if (flavors != null){
//            flavors.stream().map((item)->{
//                item.setDishId(dishId);
//                return item;
//            }).collect(Collectors.toList());
//            dishFlavorService.saveBatch(flavors);
//        }
//
//    }

//    @Override
//    @Transactional
//    public void updateWithFlavor(DishDTO dishDTO) {
//        //修改菜品信息
//        this.updateById(dishDTO);
//        //得到菜品ID，删除该id对应的所有口味
//        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dishDTO.getId());
//        dishFlavorService.remove(dishFlavorLambdaQueryWrapper);
//        //添加新的口味
//        List<DishFlavor> flavors = dishDTO.getFlavors();
//        if (flavors != null) {
//            flavors.stream().map((item) -> {
//                item.setDishId(dishDTO.getId());
//                return item;
//            }).collect(Collectors.toList());
//            dishFlavorService.saveBatch(flavors);
//        }
//    }
//
//    @Override
//    @Transactional
//    public void deleteWithFlavor(List<Long> ids) {
//
//        //判断是否在起售状态，如果删除在售商品，抛出自定义关联异常
//        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        dishLambdaQueryWrapper.eq(Dish::getStatus, 1);
//        dishLambdaQueryWrapper.in(Dish::getId, ids);
//        int count = this.count(dishLambdaQueryWrapper);
//        if (count > 0) {
//            throw new DefinedCorrelationException("包含在售商品，不可删除..");
//        }
//        //没有关联 就删除
//        this.removeByIds(ids);
//        //接着删除 对应口味
//        //得到菜品ID，删除该id对应的所有口味
//        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        dishFlavorLambdaQueryWrapper.in(DishFlavor::getDishId,ids);
//        dishFlavorService.remove(dishFlavorLambdaQueryWrapper);
//
//    }


}

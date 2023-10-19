package com.wang.goods.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.goods.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}

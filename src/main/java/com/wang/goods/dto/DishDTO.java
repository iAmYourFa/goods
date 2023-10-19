package com.wang.goods.dto;

import com.wang.goods.pojo.Dish;
import lombok.Data;

import java.util.List;

@Data
public class DishDTO extends Dish {

    private String categoryName;

}

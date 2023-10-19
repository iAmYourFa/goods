package com.wang.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.goods.common.CommonResult;
import com.wang.goods.common.DefinedCorrelationException;
import com.wang.goods.pojo.Category;
import com.wang.goods.pojo.Dish;
import com.wang.goods.service.CategoryService;
import com.wang.goods.service.DishService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    DishService dishService;


    @GetMapping("/page")
    public CommonResult<Page<Category>> page(int page, int pageSize){

        Page<Category> categoryPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Category::getSort);
        categoryService.page(categoryPage,lambdaQueryWrapper);
        return CommonResult.success(categoryPage);
    }

    @PostMapping
    public CommonResult<String> add(@RequestBody Category category){

        categoryService.save(category);
        return CommonResult.success("新增类别成功");
    }
    @PutMapping
    public CommonResult<String> update(@RequestBody Category category){

        categoryService.updateById(category);
        return CommonResult.success("更新类别成功");
    }

    @DeleteMapping
    public CommonResult<String> delete(@RequestParam("ids") Long id){

//        //1.判断删除的分类是否在商品中存在
//        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
//        int count_dish = dishService.count(dishLambdaQueryWrapper);
//        if (count_dish>0){
//            //抛出分类在商品中存在的异常
//            throw new DefinedCorrelationException("该分类已与商品关联，不得删除");
//        }
//        categoryService.removeById(id);
        return CommonResult.success("删除分类成功");
    }

    @GetMapping("/{id}")
    public CommonResult<Category> getById(@PathVariable Long id){

        Category category = categoryService.getById(id);
        if(category!=null){
            return CommonResult.success(category);
        }
        return CommonResult.error("没有查询到信息");
    }

//    @GetMapping("/list")
//    public CommonResult<List<Category>> list(String type){
//        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        categoryLambdaQueryWrapper.eq( StringUtils.isNotEmpty(type),Category::getType, type);
//        List<Category> list = categoryService.list(categoryLambdaQueryWrapper);
//        return CommonResult.success(list);
//    }
}

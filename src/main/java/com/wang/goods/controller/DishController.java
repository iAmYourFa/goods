package com.wang.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.goods.common.CommonResult;
import com.wang.goods.dto.DishDTO;
import com.wang.goods.pojo.Category;
import com.wang.goods.pojo.Dish;
import com.wang.goods.service.CategoryService;
import com.wang.goods.service.DishService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    DishService dishService;
    @Autowired
    CategoryService categoryService;
//
//    @GetMapping("/page")
//    public CommonResult<Page<DishDTO>> page(int page, int pageSize, String name){
//
//
//        //1创建dishDTO的page对象
//        Page<DishDTO> dishDTOPage = new Page<>();
//        //2创建并得到dish的page对象将对象复制到dishDTO的page中的record属性records复制到对象中 BeanUtils.copyProperties()
//        Page<Dish> dishPage = new Page<>(page, pageSize);
//        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        dishLambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
//        dishLambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
//        dishService.page(dishPage, dishLambdaQueryWrapper);
//        BeanUtils.copyProperties(dishPage,dishDTOPage,"records");
//        //3创建新关于dishDTO的Records ，给他的每一项菜品设置当前菜品的分类名称  stream().map((item)->{return item;}).collect(Collectors.toList());
//        List<DishDTO> recordsDishDTO = dishPage.getRecords().stream().map((item)->{
//            DishDTO dishDTO = new DishDTO();
//            //将record中的原始数据dish复制到新的dishDTO对象中
//            BeanUtils.copyProperties(item,dishDTO);
//            //根据菜品分类ID查找当前菜品的分类名称
//            Long categoryId = item.getCategoryId();
//            Category category = categoryService.getById(categoryId);
//            if(category!=null){
//                String categoryName = category.getName();
//                dishDTO.setCategoryName(categoryName);
//            }
//            return dishDTO;
//        }).collect(Collectors.toList());
//        //4将Records给dishDTO的page对象 并返回
//        dishDTOPage.setRecords(recordsDishDTO);
//
//        return CommonResult.success(dishDTOPage);
//    }
//    /**
//     *     url: `/dish/${id}`,
//     *     method: 'get'
//      */
//
//    @GetMapping("/{id}")
//    public CommonResult<DishDTO> get(@PathVariable Long id){
//
//        DishDTO dishDTO = dishService.getByIdWithFlavor(id);
//        return CommonResult.success(dishDTO);
//    }
//
//    @PostMapping
//    public CommonResult<String> save(@RequestBody DishDTO dishDTO){
//
//        dishService.saveWithFlavor(dishDTO);
//        return CommonResult.success("保存成功");
//    }
//
//    @PutMapping
//    public CommonResult<String> update(@RequestBody DishDTO dishDTO){
//
//        dishService.updateWithFlavor(dishDTO);
//        return CommonResult.success("更新成功");
//    }
//
//    /**
//     *   url: '/dish',
//     *     method: 'delete',
//     *     params: { ids }
//     */
//    @DeleteMapping
//    public CommonResult<String> delete(@RequestParam List<Long> ids){
//
//
//        dishService.deleteWithFlavor(ids);
//        return CommonResult.success("删除成功");
//    }
//    /**
//     *   url: `/dish/status/${params.status}`,
//     *     method: 'post',
//     *     params: { ids: params.id }
//     */
//    @PostMapping("/status/{status}")
//    public CommonResult<String> updateStatusById(@PathVariable int status, @RequestParam List<Long> ids){
//
//        Dish dish = new Dish();
//        dish.setStatus(status);
//        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        dishLambdaQueryWrapper.in(Dish::getId, ids);
//        dishService.update(dish, dishLambdaQueryWrapper);
//
//        return CommonResult.success("状态更新成功");
//    }
//
//    /**
//     *   url: '/dish/list',
//     *     method: 'get',
//     *     params
//     */
//    @GetMapping("/list")
//    public CommonResult<List<Dish>> list(@RequestParam("categoryId") Long categoryId){
//
//        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        dishLambdaQueryWrapper.eq(Dish::getCategoryId, categoryId);
//        List<Dish> list = dishService.list(dishLambdaQueryWrapper);
//        return CommonResult.success(list);
//    }
}

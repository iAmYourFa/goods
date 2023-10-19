package com.wang.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.goods.common.CommonResult;
import com.wang.goods.pojo.Brand;
import com.wang.goods.service.BrandService;
import com.wang.goods.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;
    @Autowired
    DishService dishService;

    @GetMapping("/page")
    public CommonResult<Page<Brand>> page(int page, int pageSize){

        Page<Brand> brandPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Brand> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Brand::getSort);
        brandService.page(brandPage,lambdaQueryWrapper);
        return CommonResult.success(brandPage);
    }

    @PostMapping
    public CommonResult<String> add(@RequestBody Brand brand){

        brandService.save(brand);
        return CommonResult.success("新增品牌成功");
    }
    @PutMapping
    public CommonResult<String> update(@RequestBody Brand brand){

        brandService.updateById(brand);
        return CommonResult.success("更新品牌成功");
    }

    @DeleteMapping
    public CommonResult<String> delete(@RequestParam("ids") Long id){

//        //1.判断删除的品牌是否在商品中存在
//        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        dishLambdaQueryWrapper.eq(Dish::getBrandId,id);
//        int count_dish = dishService.count(dishLambdaQueryWrapper);
//        if (count_dish>0){
//            //抛出品牌在商品中存在的异常
//            throw new DefinedCorrelationException("该品牌已与商品关联，不得删除");
//        }
//        brandService.removeById(id);
        return CommonResult.success("删除品牌成功");
    }

    @GetMapping("/{id}")
    public CommonResult<Brand> getById(@PathVariable Long id){

        Brand brand = brandService.getById(id);
        if(brand!=null){
            return CommonResult.success(brand);
        }
        return CommonResult.error("没有查询到信息");
    }

//    @GetMapping("/list")
//    public CommonResult<List<Brand>> list(String type){
//        LambdaQueryWrapper<Brand> brandLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        brandLambdaQueryWrapper.eq( StringUtils.isNotEmpty(type),Brand::getType, type);
//        List<Brand> list = brandService.list(brandLambdaQueryWrapper);
//        return CommonResult.success(list);
//    }
}

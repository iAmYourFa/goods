package com.wang.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.goods.mapper.BrandMapper;
import com.wang.goods.pojo.Brand;
import com.wang.goods.service.BrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}

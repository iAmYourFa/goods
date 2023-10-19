package com.wang.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.goods.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}

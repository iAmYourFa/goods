package com.wang.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.goods.common.CommonResult;
import com.wang.goods.pojo.Employee;
import com.wang.goods.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 'url': '/employee/login',
     * 'method': 'post',
     *
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public CommonResult<Employee> login(HttpServletRequest httpServletRequest, @RequestBody Employee employee) {

        //得到用户名，根据用户查询数据库DigestUtils.md5DigestAsHex(password.getBytes());
        String username = employee.getUsername();
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername, username);
        Employee one = employeeService.getOne(employeeLambdaQueryWrapper);
        //如果没得到 ，返回用户不存在
        if (one == null) return CommonResult.error("用户不存在");
//        得到
        // 将密码进行md5加密
        String password = employee.getPassword();
        String md5_username = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println("------------------" + md5_username);
        //加密后的密码与数据库密码作对比
        //如果不一样，返回密码错误
        if (!one.getPassword().equals(md5_username)) return CommonResult.error("密码错误");
//        一样
        //查看账户是否已禁用 ....
        if (one.getStatus() == 0) return CommonResult.error("账户已禁用");
        //...
        //将 用户保存到Session中，设置超时时间httpServletRequest
        httpServletRequest.getSession().setAttribute("employee", one.getId());
        return CommonResult.success(one);
    }

    /**
     * 'url': '/employee/logout',
     * 'method': 'post',
     *
     * @return
     */
    @PostMapping("/logout")
    public CommonResult<String> logout(HttpServletRequest httpServletRequest) {

        //得到当前session并删除
        httpServletRequest.getSession().removeAttribute("employee");
        return CommonResult.success("退出成功");
    }

    /**
     * url: '/employee/page',
     * method: 'get',
     * params = {page: this.page,
     * pageSize: this.pageSize,
     * name: this.input ? this.input : undefined}
     */
    @GetMapping("/page")
    public CommonResult<Page<Employee>> page(int page, int pageSize, String name) {

        //分页构造器
        Page<Employee> employeePage = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        //组合分页构造器和条件构造器
        employeeService.page(employeePage, lambdaQueryWrapper);
//      返回分页对象
        return CommonResult.success(employeePage);
    }

    /**
     * // 修改---启用禁用接口
     * url: '/employee',
     * method: 'put',
     */

    @PutMapping
    public CommonResult<String> update(@RequestBody Employee employee) {

        employeeService.updateById(employee);
        return CommonResult.success("更新员工成功");
    }

    // 新增---添加员工
//        url: '/employee',
//        method: 'post',

    @PostMapping
    public CommonResult<String> add(@RequestBody Employee employee){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.save(employee);
        return CommonResult.success("新增员工成功(密码默认123456)");
    }
    // 修改页面反查详情接口
//        url: `/employee/${id}`,
//        method: 'get'
    @GetMapping("/{id}")
    public CommonResult<Employee> getById(@PathVariable Long id){

        Employee employee = employeeService.getById(id);
        if(employee!=null){
            return CommonResult.success(employee);
        }
        return CommonResult.error("没有查询到信息");
    }

}
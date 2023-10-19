package com.wang.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.goods.common.CommonResult;
import com.wang.goods.pojo.User;
import com.wang.goods.service.UserService;
import com.wang.goods.utils.ValidateCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     *      'url': '/user/sendMsg',
     *         'method': 'post',
     *         data
     */
    @PostMapping("/sendMsg")
    public CommonResult<String> sendMsg(@RequestBody User user, HttpServletRequest httpServletRequest){

        //得到用户在前端传来的手机号，判断是否为空
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            //调用验证码生成工具，获得四位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //保存用户登录的session将手机号作为key,验证码作为value
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(phone, code);
            session.setMaxInactiveInterval(60);
            //调用SMSUtils为手机发送短信(这里直接模拟将验证码发送到控制台)
            System.out.println("测试验证码：您的验证码为："+code+"，有效时间60秒，请勿泄露他人！");
            return CommonResult.success("手机验证码发送成功");
        }

        return CommonResult.success("手机验证码发送失败");

    }

    /**
     *  'url': '/user/login',
     *       'method': 'post',
     *       data
     */
    @PostMapping("/login")
    public CommonResult<User> login(@RequestBody Map<String, String> form, HttpServletRequest httpServletRequest){

        System.out.println(form);
        //得到用户根据短信在前端输入的手机号和验证码，
        String phone = form.get("phone");
        String code = form.get("code");
        //从session中的手机号key和验证码value ，根据用户输入的手机号，得到验证码
        HttpSession session = httpServletRequest.getSession();
        Object captcha_code = session.getAttribute(phone);
        //将前端验证码和后端验证码对比，如果不一样或者为空，返回验证码错误
        if (captcha_code == null || !captcha_code.toString().equals(code)){
            return CommonResult.error("验证码错误");
        }
        //如果格式正确，则判断是否为新用户
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone, phone);
        User user = userService.getOne(userLambdaQueryWrapper);
        //如果是新用户，设置它的用户名和密码
        if (user == null){
            user.setName("用户" +  UUID.randomUUID());
            user.setPhone(phone);
            user.setStatus(1);
        }
        //设置用户session,删除临时验证
        session.setAttribute("user",user.getId());
        session.setMaxInactiveInterval(30*60);
        session.removeAttribute("phone");
        return CommonResult.success(user);
    }

    /**
     *   'url': '/user/loginout',
     *     'method': 'post',
     */
    @PostMapping("/loginout")
    public CommonResult<String> loginout( HttpServletRequest httpServletRequest){

        //得到当前session并删除
        httpServletRequest.getSession().removeAttribute("user");
        return CommonResult.success("用户已退出");
    }
}

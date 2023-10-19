package com.wang.goods.filter;

import com.alibaba.fastjson.JSON;
import com.wang.goods.common.BaseContext;
import com.wang.goods.common.CommonResult;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //获取当前请求的请求路径
        String requestURI = httpServletRequest.getRequestURI();
        //提供需要放行的的路径
        String [] doFilterURL = {
                "/backend/**",
                "/employee/login",
                "/employee/logout",
        };
        //使用Spring工具提供的路径匹配器PATH_MATCHER匹配路径
        boolean match = false;
        for (String url:doFilterURL) {
            match = PATH_MATCHER.match(url, requestURI);
            if (match) break;

        }
        //判断如果与需要放行的路径相同，则放行，直接return
        if (match) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //如果不是
//        判断用户是否登录，若登录，就放行
        Long empId = (Long)httpServletRequest.getSession().getAttribute("employee");
        if(empId!=null){
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //没有登陆，跳转到登录页面，返回未登录消息
        httpServletResponse.getWriter().write(JSON.toJSONString(CommonResult.error("NOTLOGIN")));
    }
}

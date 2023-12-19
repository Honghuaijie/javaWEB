package com.hhj.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ClassName: Demo01Filter
 * Package: com.hhj.filters
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/4 10:21
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */

//需要指定要拦截的请求
//@WebFilter("/demo01.do")
@WebFilter("*.do")  //拦截所有请求
public class Filter01 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //还没有放行之前执行的代码
        System.out.println("A");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        //放行之后执行的代码
        System.out.println("A2");
    }

    @Override
    public void destroy() {

    }
}

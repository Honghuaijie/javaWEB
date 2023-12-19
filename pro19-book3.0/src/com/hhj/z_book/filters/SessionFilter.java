package com.hhj.z_book.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebFault;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: SessionFilter
 * Package: com.hhj.z_book.filters
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/11 13:48
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
// 如果想让页面在没有curruser的情况下也能访问，就将该页面加入白名单中
//@WebFilter(urlPatterns = {"*.do","*.html"},
//    initParams = {
//        @WebInitParam(name="bai",
//                value="/pro19/page.do?operate=page&page=user/login,/pro19/user.do?null")
//        })
public class SessionFilter implements Filter {
    List<String> baiList = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String bai = config.getInitParameter("bai");
        String[] baiArr = bai.split(",");
        baiList = Arrays.asList(baiArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)  servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String queryStr = request.getQueryString();
        String str = uri + "?" + queryStr;
        if (baiList.contains(str)){
            filterChain.doFilter(request,response);
        }else{
            HttpSession session = request.getSession();
            Object currUserObj = session.getAttribute("currUser");
            if (currUserObj == null){
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{
                filterChain.doFilter(request,response);
            }
        }


    }

    @Override
    public void destroy() {

    }
}

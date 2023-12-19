package com.atguigu.cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: CookieServlet01
 * Package: com.atguigu.cookies.servlet
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/11 15:24
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */

@WebServlet("/cookie01")
public class CookieServlet01 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建一个cookie对象
        Cookie cookie = new Cookie("uname1","jim1");
        //2.将这个cookie对象保存到浏览器端
        response.addCookie(cookie);

        request.getRequestDispatcher("hello01.html").forward(request,response);

    }
}

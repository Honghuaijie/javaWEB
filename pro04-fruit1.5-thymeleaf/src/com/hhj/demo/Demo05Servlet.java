package com.hhj.demo;

import javafx.application.Application;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Package: com.hhj.demo
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/27 12:25
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
//演示application保存作用域（demo05和demo06)
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.向application保存作用域
        //指的是servlet上下文
        ServletContext application = request.getServletContext();
        application.setAttribute("uname","lili3");

        //2.重定向
        response.sendRedirect("demo06");
        //3.服务端转发
//        request.getRequestDispatcher("demo04").forward(request,response);

    }
}

package com.hhj.demo;

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
//演示session保存作用域（demo03和demo04)
@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("uname","lili2");

        //重定向
        response.sendRedirect("demo04");
        //服务端转发
//        request.getRequestDispatcher("demo04").forward(request,response);

    }
}

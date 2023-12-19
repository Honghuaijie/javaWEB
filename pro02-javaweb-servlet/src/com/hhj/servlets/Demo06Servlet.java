package com.hhj.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: Demo06Servlet
 * Package: com.hhj.servlets
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/26 16:29
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
//演示服务器端内部转发，以及客户端重定向
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo06....");
        //服务器端内部转发
        //request.getRequestDispatcher("demo07").forward(request,response);
        //客户端重定向(服务器告诉客户端应该访问那个URL所以应该是response)
        //设置作用域，让demo07读取
        HttpSession session = request.getSession();
        session.setAttribute("uname","lina");
        response.sendRedirect("demo07");
    }
}

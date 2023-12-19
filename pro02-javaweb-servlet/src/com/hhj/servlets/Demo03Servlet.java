package com.hhj.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: Demo03Servlet
 * Package: com.hhj.servlets
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/23 21:56
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class Demo03Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session,如果获取不到就创建一个新的
        HttpSession session = request.getSession();
        System.out.println("session ID:" + session.getId());
        session.setMaxInactiveInterval(60); //

    }
}

package com.hhj.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: Demo04Servlet
 * Package: com.hhj.servlets
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/24 19:56
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
//演示将httpSession保存数据
public class Demo04Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        session.setMaxInactiveInterval(30);
        session.setAttribute("uname","lina");
//        session.remove
    }
}

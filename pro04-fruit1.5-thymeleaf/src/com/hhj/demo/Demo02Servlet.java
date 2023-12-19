package com.hhj.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo02Servlet
 * Package: com.hhj.demo
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/27 12:26
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebServlet("/demo02")
public class Demo02Servlet  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uname = request.getAttribute("uname");
        System.out.println("uname:" + uname);

    }
}

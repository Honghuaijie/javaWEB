package com.atguigu.cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: KaptchaServletDemo01
 * Package: com.atguigu.cookies.servlet
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/13 8:06
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebServlet("/kaptcha01")
public class KaptchaServletDemo01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("KAPTCHA_SESSION_KEY");
        System.out.println("obj = " + obj);

    }
}

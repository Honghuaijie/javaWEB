package com.hhj.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo02Servlet
 * Package: com.hhj.servlets
 * Description:  演示servlet的生命周期
 *
 * @Author honghuaijie
 * @Create 2023/10/23 21:40
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class Demo02Servlet extends HttpServlet {

    public Demo02Servlet() {
        System.out.println("正在实例化");
    }
    @Override
    public void init() throws ServletException {

        System.out.println("正在初始化.....");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在服务.....");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁.....");
    }
}

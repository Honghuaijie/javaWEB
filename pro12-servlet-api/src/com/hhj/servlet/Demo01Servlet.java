package com.hhj.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Package: com.hhj.servlet
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/2 19:46
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
//@WebServlet(urlPatterns = {"/demo01"},
//        initParams = {
//            @WebInitParam(name="hello",value="word"),
//            @WebInitParam(name="uname",value="jim")
//        }
//        )
public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("initvalue:" + initValue);
        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation" + contextConfigLocation);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("servlet:" + contextConfigLocation);
    }
}

//servlet的声明周期：实例化，初始化，服务，销毁

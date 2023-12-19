package com.hhj.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: MyServletContextListener
 * Package: com.hhj.listener
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/5 9:23
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Servlet上下文对象初始化动作被我监听到了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Servlet上下文对象销毁动作被我监听到了");

    }
}

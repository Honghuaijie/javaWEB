package come.hhj.myssm.listener;

import come.hhj.myssm.ioc.BeanFactory;
import come.hhj.myssm.ioc.ClassPathXMLApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: ContextLoaderListener
 * Package: com.hhj.myspringmvc.listener
 * Description:
 *  从在初始化context上下文时就加载bean工厂
 * @Author honghuaijie
 * @Create 2023/11/5 9:42
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
//监听上下文启动，在上下文启动的时候去创建IOC容器，然后将其保存到application作用作用域
// 后面中央控制器再从application中获取IOC容器
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //1.获取application
        ServletContext application = servletContextEvent.getServletContext();
        //2.从web.xml中获取bean工厂的配置文件
        //获取上下文的初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        //3.创建IOC容器
        BeanFactory beanFactory = new ClassPathXMLApplicationContext(path);
        //4.将IOC容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

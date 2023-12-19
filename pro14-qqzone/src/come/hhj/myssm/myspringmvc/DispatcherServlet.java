package come.hhj.myssm.myspringmvc;

import come.hhj.myssm.ioc.BeanFactory;
import come.hhj.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * ClassName: DispatcherServlet
 * Package:
 * Description:
 * 中央控制器有三大步
 * 1.根据请求的URL 获取（servletPath） 并解析来 获取到控制器的名字
 * 2.获取applicationContext.xml中的beean中的id和classname来获取class类对象 保存在Map中
 * 3.根据控制器的名字来对应map中的对象,并通过反射来调用指定的方法（方法是谁  是通过operate来决定）
 * <p>
 * 注意：URL中请求的控制器名字，必须和applicationContext.xml中的bean的id属性值一一对应
 *
 * @Author honghuaijie
 * @Create 2023/10/30 15:07
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private BeanFactory beanFactory  ;
    //解析applicationContext.xml这个配置文件
    @Override
    public void init() throws ServletException {
        super.init();
        //之前是在此处主动创建IOC容器的
        //现在优化为从application作用域去获取
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if (beanFactoryObj !=null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else{
            throw new RuntimeException("IOC容器获取失败！");
        }
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.第一步： /hello.do 通过字符串截取得到hello
        String servletPath = request.getServletPath();
        //去掉/
        servletPath = servletPath.substring(1);
        //去掉.do
        int lastDoIndex = servletPath.lastIndexOf(".do"); //从后面开始找，找到倒数第一个.do的下标
        //获取控制器的名字
        servletPath = servletPath.substring(0, lastDoIndex);
        //2.hello - > helloController 通过控制器的名字去调用指定的控制器
        //由于在构造器中已经获取了全部的controller名字加上对应的类，此时我们直接调用就可以了
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        //获取要执行的方法名
        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        try {
            //通过循环的方式获取方法
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())) {
                    //1.统一获取请求参数
                    //1-1获取当前方法的形参列表，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //1-2创建一个和参数数组同等长度数组，用来存放参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        //获取参数
                        Parameter parameter = parameters[i];
                        //通过参数名，来获取值
                        //参数值分为两大类，一类是请求中的值，一类是request、response等
                        //获取参数的名字
                        String parameterName = parameter.getName();
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            //通过参数的名字，从请求中获取参数值
                            String parameterValue = request.getParameter(parameterName);
                            //获取参数的类型名
                            String typeName = parameter.getType().getName();
                            Object parameterObj = parameterValue;
                            if (parameterObj !=null){
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            parameterValues[i] = parameterObj;  //存储的是"2" 而不是 2

                        }
                    }
                    //2.controller组件中的方法调用
                    method.setAccessible(true);
                    Object methodObj = method.invoke(controllerBeanObj, parameterValues);
                    String methodReturnStr = "";
                    if (methodObj != null) {
                        methodReturnStr = (String) methodObj;
                    }
                    //3.视图处理
                    if (methodReturnStr.startsWith("redirect:")) { //说明返回的是一个重定向的操作
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnStr, request, response);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherExpection("dispatcher层出问题了");
        }
    }
}

//常见错误：IllegalArgumentException: argument type mismatch
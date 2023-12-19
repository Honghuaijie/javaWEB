package com.hhj1.myspringmvc;

import com.hhj1.utils.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DispatchServlet
 * Package: com.hhj1.myspringmvc
 * Description:
 *      用来管理所有的servlet
 * @Author honghuaijie
 * @Create 2023/11/2 17:22
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebServlet("*.do")
public class DispatchServlet extends ViewBaseServlet {
    //使用map来存储每一个控制器的对象
    private Map<String, Object> beanMap = new HashMap<>();


    //初始化
    //解析applicationContext.xml这个配置文件
    public DispatchServlet() {
        try {
            //获取类加载器去读取配置文件
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建DocumentBuilderFactory 这个对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder 这个对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //3.创建document对象(前两步的目的就是为了创建doucment对象)
            Document document = documentBuilder.parse(inputStream);
            //4.通过标签名获取所有bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);  //获取单个beanNode
                //判断这个beeanNode是不是一个元素节点
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id"); //获取bean中的ID属性值
                    String className = beanElement.getAttribute("class"); //获取bean中的class属性值
                    //通过反射获取className这个类，并创建className的对象
                    Object beanObj = Class.forName(className).newInstance();
                    beanMap.put(beanId, beanObj);

                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1. 获取控制器的名字
        //假如URL是http://localhost:8080/pro08/hello.do
        //那么 servletPath是 ： /hello.do
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        servletPath = servletPath.substring(0,servletPath.lastIndexOf(".do"));

        //2. 通过控制器的名字，来获取控制器的对象
        Object controllerBeanObj = beanMap.get(servletPath);

        //获取要执行的方法名
        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
        for (Method method: methods){
            if (method.getName().equals(operate)){  //找到要执行的方法了
                //如何调用方法？
                //因为方法的形参个数和类型不一样
                //方法的形参名和请求中的参数名是一致的

                //获取方法的形参列表（方法的形参分为两种，一种是请求，一种是值）
                Parameter[] parameters = method.getParameters();
                //存放形参值
                Object[] parameterValues = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    //获取参数
                    Parameter parameter = parameters[i];
                    //
                    String parameName = parameter.getName();
                    //获取形参名来判断需要放什么参数进去
                    if ("request".equals(parameName)){
                        parameterValues[i] = request;
                    }else if ("response".equals((parameName))){
                        parameterValues[i] = response;
                    }else { //需要从请求中获取值
                        //获取参数的值
                        String parameterValue = request.getParameter(parameName);
                        //获取参数的类型名
                        String parameterTypeName = parameter.getType().getName();
                        Object parameterObj = parameterValue;
                        if (parameterValue !=null && "java.lang.Integer".equals(parameterTypeName)){
                            parameterObj = Integer.parseInt(parameterValue);
                        }
                        parameterValues[i] = parameterObj;
                    }
                }
                //2.controller组件中的方法调用
                method.setAccessible(true);
                try {
                    Object methodObj = method.invoke(controllerBeanObj, parameterValues);
                    String methodReturnStr = "";
                    if (methodObj !=null){
                        methodReturnStr = (String) methodObj;
                    }
                    //3.根据方法的返回值来判断是进行渲染操作还是重定向操作
                    if (methodReturnStr.startsWith("redirect:")){
                        response.sendRedirect(methodReturnStr.substring("redirect:".length()));
                    }else{
                        super.processTemplate(methodReturnStr,request,response);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}

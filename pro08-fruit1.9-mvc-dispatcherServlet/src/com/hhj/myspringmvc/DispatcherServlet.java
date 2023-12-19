package com.hhj.myspringmvc;

import com.hhj.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.plugin.com.BeanClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DispatcherServlet
 * Package: com.hhj.myspringmvc
 * Description:
 *  中央控制器有三大步
 *  1.根据请求的URL 获取（servletPath） 并解析来 获取到控制器的名字
 *  2.获取applicationContext.xml中的beean中的id和classname来获取class类对象 保存在Map中
 *  3.根据控制器的名字来对应map中的对象,并通过反射来调用指定的方法（方法是谁  是通过operate来决定）
 *
 * 注意：URL中请求的控制器名字，必须和applicationContext.xml中的bean的id属性值一一对应
 * @Author honghuaijie
 * @Create 2023/10/30 15:07
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    private Map<String,Object> beanMap = new HashMap<>();

    //解析applicationContext.xml这个配置文件
    public DispatcherServlet() {
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
            NodeList beanNodeList =  document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);  //获取单个beanNode
                //判断这个beeanNode是不是一个元素节点
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement =  (Element) beanNode;
                    String beanId = beanElement.getAttribute("id"); //获取bean中的ID属性值
                    String className = beanElement.getAttribute("class"); //获取bean中的class属性值
                    //通过反射获取className这个类，并创建className的对象
                    Object beanObj = Class.forName(className).newInstance();
                    beanMap.put(beanId,beanObj);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (SAXException e) {
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
        //设置编码
        request.setCharacterEncoding("utf-8");
        //假如URL是http://localhost:8080/pro08/hello.do
        //那么 servletPath是 ： /hello.do
        //我的思路是：
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
        Object controllerBeanObj = beanMap.get(servletPath);


        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate = "index";
        }

        //通过getDeclaredMethod获取到方法
        try {
            Method method = controllerBeanObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
            if (method !=null){
                //由于方法都是private,所以需要将权限去掉
                method.setAccessible(true);
                method.invoke(controllerBeanObj,request,response);
            }else{
                throw  new RuntimeException("operate非法!");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

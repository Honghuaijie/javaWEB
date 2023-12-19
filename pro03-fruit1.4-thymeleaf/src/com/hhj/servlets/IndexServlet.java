package com.hhj.servlets;


import com.hhj.fruits.Fruit;
import com.hhj.fruits.dao.FruitDao;
import com.hhj.fruits.impl.FruitDaoImpl;
import com.hhj.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: IndexServlet
 * Package: com.hhj.servlets
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/26 17:14
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet  extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FruitDao fruitDao = new FruitDaoImpl();
        List<Fruit> fruitList = fruitDao.getFruitList();
        //保存到session作用域
        request.getSession().setAttribute("fruitList",fruitList);

        //此处的视图名称是index
        //那么thymeleaf会将这个逻辑视图名称 对应到 物理视图名称上
        //逻辑视图名称： index
        //物理视图名称： view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是 /index.html
        super.processTemplate("index",request,response);

    }
}

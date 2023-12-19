package com.hhj.servlets;

import com.hhj.fruit.dao.FruitDAO;
import com.hhj.fruit.dao.impl.FruitDAOImpl;
import com.hhj.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.sql.SQLException;


public class AddServlet extends HttpServlet {
    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //将接受到的值添加到数据库中
        FruitDAO fruitDAO = new FruitDAOImpl();
        boolean flag = fruitDAO.addFruit(new Fruit(0 , fname , price , fcount , remark));

        System.out.println(flag ? "添加成功！" : "添加失败！");



    }
}

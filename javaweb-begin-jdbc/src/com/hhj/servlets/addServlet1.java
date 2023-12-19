package com.hhj.servlets;

import com.hhj.utils.BaseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * ClassName: addServlet1
 * Package: com.hhj.servlets
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/23 19:28
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class addServlet1 extends HttpServlet {
    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        //get方式下，不需要设置编码（基于Tomcat8）
        //如果是get请求发送的中文数据，转码稍微有点麻烦（tomcat8之前）
        String fname = request.getParameter("fname")
        //1.将字符串打散成字节数组
        byte[] bytes = fname.getBytes("ISO-8859-1");
        //2.将字节数组按照设定的编码重新组装成字符串
        fname = new String(bytes,"utf-8");
        */

        //post方式下，设置编码，防止中文乱码
        //需要注意的是，设置编码这一句代码必须在所有获取参数动作之前
        request.setCharacterEncoding("utf-8");
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");


        //将接受到的值添加到数据库中
        String sql  = "insert into t_fruit values(0,?,?,?,?);";
        int i = 0;
        try {
            i = BaseDao.executeUpdata(sql, fname, price, fcount, remark);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(i>0 ? "添加成功！" : "添加失败");

    }
}

package com.hhj.servlets;

import com.hhj.fruits.Fruit;
import com.hhj.fruits.dao.FruitDao;
import com.hhj.fruits.impl.FruitDaoImpl;
import com.hhj.myspringmvc.ViewBaseServlet;
import com.hhj.utils.BaseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * ClassName: UpdataServlet
 * Package: com.hhj.servlets
 * Description:
 *      根据post发送过来的消息，更改，水果类中对应记录的值
 * @Author honghuaijie
 * @Create 2023/10/28 15:00
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */

@WebServlet("/updata.do")
public class UpdataServlet  extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码：post需要更改字符编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        // 获取ID
        String fidStr = request.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        //获取fname
        String fname = request.getParameter("fname");
        //获取price
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        //获取库存
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        //获取备注
        String remark = request.getParameter("remark");
        //3.执行更新
        // 修改数据库中对应记录的信息
        FruitDao fruitDao = new FruitDaoImpl();
        fruitDao.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        //4、资源跳转
        //注意不能使用request.getRequestDispatcher('index').format(..)因为两个请求是不一样，无法进行转发
        //修改完成之后回到index页面
        //此处需要重定向，目的是重新给indexServlet发请求，
        // 获取更新后的数据库信息然后覆盖到session作用域，
        // 这样index.html页面上显示的数据才是最新的
        response.sendRedirect("index");
        //这里是跳转到/index.html ，而我们需要调整到index上
//        super.processTemplate("index",request,response);


    }
}

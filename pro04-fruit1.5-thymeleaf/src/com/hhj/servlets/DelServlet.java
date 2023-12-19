package com.hhj.servlets;

import com.hhj.fruits.dao.FruitDao;
import com.hhj.fruits.impl.FruitDaoImpl;
import com.hhj.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: DelServlet
 * Package: com.hhj.servlets
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/28 16:36
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebServlet("/del.do")
public class DelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取到fid
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpt(fidStr)){
            int fid = Integer.parseInt(fidStr);
            //从数据库中删除id
            FruitDao fruitDao = new FruitDaoImpl();
            fruitDao.delFruit(fid);
            //资源跳转
            response.sendRedirect("index");
        }
    }
}

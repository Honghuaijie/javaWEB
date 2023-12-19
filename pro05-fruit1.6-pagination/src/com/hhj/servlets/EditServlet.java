package com.hhj.servlets;

import com.hhj.fruits.Fruit;
import com.hhj.fruits.dao.FruitDao;
import com.hhj.fruits.impl.FruitDaoImpl;
import com.hhj.myspringmvc.ViewBaseServlet;
import com.hhj.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: EditServlet
 * Package: com.hhj.servlets
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/27 16:54
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取发过来的值
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpt(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDao.getFruitByFid(fid);
            request.setAttribute("fruit",fruit); //将fruit对象保存在请求作用域下
            //跳转到/edit.html页面下
            super.processTemplate("edit",request,response);

        }

    }
}

package com.hhj.servlets;


import com.hhj.fruits.Fruit;
import com.hhj.fruits.dao.FruitDao;
import com.hhj.fruits.impl.FruitDaoImpl;
import com.hhj.myspringmvc.ViewBaseServlet;
import com.hhj.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //由于涉及到dopost，所以需要设置编码
        request.setCharacterEncoding("utf-8");
        //通过oper 来判断该请求是查询操作还是非查询操作
        //oper == null 说明该请求是非查询操作
        String oper = request.getParameter("oper");
        HttpSession session = request.getSession();
        //通过get的方法来获取页码，默认为1
        Integer pageNo = 1;
        String keyword = null;
        //表示该请求是查询操作
        if (StringUtil.isNotEmpt(oper) && "search".equals(oper)){
            pageNo=1;
            //keyword应该从请求中获取
            keyword = request.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)){
                keyword="";
            }
            //将keyword存放到session作用域中
            session.setAttribute("keyword",keyword);
        }else{
            //表示当前查询是不是查询操作
            //获取页码
            String pageStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpt(pageStr)){
                pageNo = Integer.parseInt(pageStr);
            }
            //看下keyword关键字是否存在，如果存在则显示和关键字有关的记录
            Object keywordObj =  session.getAttribute("keyword");
            if (keywordObj != null){
                keyword = (String) keywordObj;
            }else{
                keyword = "";
            }


        }



        FruitDao fruitDao = new FruitDaoImpl();
        //将pageNo存放到作用域
        session.setAttribute("pageNo",pageNo);

        //获取指定页数上的记录
        List<Fruit> fruitList = fruitDao.getFruitList(keyword,pageNo);
        //获取总页数
        int fruitCount = fruitDao.getFruitCount(keyword);
        /**
         * 总记录条数     总页数
         *  1             1
         *  5             1
         *  6             2
         *  10            2
         *  11            3
         *  fruitcount    (fruitcount+5-1)/5
         */
        //保存到记录到session作用域
        session.setAttribute("fruitList",fruitList);
        //保存总页数到session作用域
        session.setAttribute("pageCount",(fruitCount+4)/5);
        //此处的视图名称是index
        //那么thymeleaf会将这个逻辑视图名称 对应到 物理视图名称上
        //逻辑视图名称： index
        //物理视图名称： view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是 /index.html
        super.processTemplate("index",request,response);

    }
}

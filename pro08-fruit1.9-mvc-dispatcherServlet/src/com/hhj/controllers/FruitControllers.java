package com.hhj.controllers;

import com.hhj.fruits.Fruit;
import com.hhj.fruits.dao.FruitDao;
import com.hhj.fruits.impl.FruitDaoImpl;
import com.hhj.myspringmvc.ViewBaseServlet;
import com.hhj.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * ClassName: FruitServlet
 * Package: com.hhj.servlets
 * Description:
 *      将多个servlet继承到一个FruitServlet中
 * @Author honghuaijie
 * @Create 2023/10/30 13:50
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */

public class FruitControllers extends ViewBaseServlet {

    /*
    *  之前fruitServlet是一个servlet组件，那么其中的init方法一定会被调用
    * 之前的init方法内部会出现一句话：super.init();
    *
    * */
    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        fruitDao.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        //4、资源跳转
        //返回到index页面上,由于没有指定operate的值，所以默认是index
        response.sendRedirect("fruit.do");


    }

    //渲染edit.html的
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取到fid
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpt(fidStr)){
            int fid = Integer.parseInt(fidStr);
            //从数据库中删除id
            FruitDao fruitDao = new FruitDaoImpl();
            fruitDao.delFruit(fid);
            //资源跳转
            //由于没有指定operate，所以默认是index
            response.sendRedirect("fruit.do" );
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页面上输入的信息
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");
        //添加到数据库中
        fruitDao.insertFruit(new Fruit(0,fname,price,fcount,remark));
        //跳转到index页面
        //获取当前页
        int pageNo = (Integer) request.getSession().getAttribute("pageNo");
        response.sendRedirect("fruit.do?pageNo=" + pageNo);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过oper 来判断该请求是查询操作还是非查询操作
        //oper == null 说明该请求是非查询操作
        String oper = request.getParameter("oper");
        HttpSession session = request.getSession();
        //通过get的方法来获取页码，默认为1
        Integer pageNo = 1;
        String keyword;
        //表示该请求是查询操作
        if (StringUtil.isNotEmpt(oper) && "search".equals(oper)){
            pageNo=1;
            //keyword应该从请求中获取
            keyword = request.getParameter("keyword");
            //keyword有两种情况，一种是为null，还有一种是存在值
            //如果是为null 我们期望的是查询%% 而不是%null%
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
            //如果不是点击的查询按钮，那么查询是基于session中现有的keyword进行查询
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
        //重新更新当前页的值
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

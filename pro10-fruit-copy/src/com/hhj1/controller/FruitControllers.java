package com.hhj1.controller;

import com.hhj1.fruits.Fruit;
import com.hhj1.fruits.dao.FruitDao;
import com.hhj1.fruits.impl.FruitDaoImpl;
import com.hhj1.utils.StringUtil;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.List;

/**
 * ClassName: FruitControllers
 * Package: com.hhj1.servlet
 * Description:
 *      水果类的servlet，里面包含了增删改查
 * @Author honghuaijie
 * @Create 2023/11/2 16:52
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */

public class FruitControllers{
    FruitDao fruitDao = new FruitDaoImpl();

    //查和显示
    private String index(String oper, String keyword,Integer pageNo,HttpServletRequest request)  {
        HttpSession session = request.getSession();
        if (pageNo == null){
            pageNo = 1;
        }

        if (StringUtil.isNotEmpt(oper) && "search".equals(oper)){
            if (StringUtil.isEmpty(keyword)){
                keyword="";
            }
        }else{
            //从作用域中取出keyword
            Object keywordObj =  session.getAttribute("keyword");
            if (keywordObj != null){
                keyword = (String) keywordObj;
            }else{
                keyword = "";
            }
        }

        //从数据库中获取数据，输出到控制台上
        List<Fruit> fruitList = fruitDao.getFruitList(keyword, pageNo);
        //从数据库中获取记录的总数量
        int fruitCount = fruitDao.getFruitCount(keyword);
        //获取session
        //保存记录到作用域中
        session.setAttribute("fruitList",fruitList);
        //保存总数量到作用域中
        session.setAttribute("pageCount",(fruitCount+4)/5);
        //将当前页更新到session作用域中
        session.setAttribute("pageNo",pageNo);
        //将keyword也保存到作用域中
        session.setAttribute("keyword",keyword);
        return "index";
    }



    //增
    private String add(String fname,Integer price, Integer fcount, String remark)  {
        //保存到数据库中
        fruitDao.insertFruit(new Fruit(0,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }

    //删
    private String delete(Integer fid)  {
        if (fid !=null){
            fruitDao.delFruit(fid);
            return "redirect:fruit.do";

        }
        //删除数据库中的数据
        return "error";

    }

    //改
    //1.渲染edit页面
    private String  edit(Integer fid,HttpServletRequest request) {
        if (fid !=null){
            Fruit fruit = fruitDao.getFruitByFid(fid);
            request.getSession().setAttribute("fruit",fruit);
            return "edit";

        }
        return  "error";

    }

    //2.修改操作
    private String  update(Integer fid,String fname,Integer price,Integer fcount,String remark,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fruitDao.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }


}

package com.hhj.controllers;

import com.hhj.fruits.Fruit;
import com.hhj.fruits.exception.FruitServiceException;
import com.hhj.fruits.service.FruitService;
import com.hhj.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 控制层
 * 通过控制层去操作业务层，而业务层去操作DAO层
 */
public class FruitControllers {
    //虽然这里赋值为null但是在ClassPathXMLApplicationContext中我们通过反射已经将依赖设置好了
    private FruitService fruitService = null;

    private String update(Integer fid,String fname,Integer price, Integer fcount,String remark) {
        //3.执行更新
        try {
            fruitService.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        }catch (Exception e){
            e.printStackTrace();
            throw new FruitServiceException("Controller层出问题了");
        }
        //4、资源跳转
        return "redirect:fruit.do";

    }
    //渲染edit.html的
    private String edit(Integer fid,HttpServletRequest request)  {
        //获取发过来的值
        if (fid !=null){
            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit",fruit); //将fruit对象保存在请求作用域下
            return "edit";
        }
        return "error";
    }
    private String del(Integer fid) {
        //获取到fid
        if (fid !=null){
            //从数据库中删除id
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname, Integer price,Integer fcount,String remark,HttpServletRequest request)  {
        //添加到数据库中
        try {
            fruitService.insertFruit(new Fruit(0,fname,price,fcount,remark));
            int pageNo = (Integer) request.getSession().getAttribute("pageNo");
            //response.sendRedirect("fruit.do?pageNo=" + pageNo);
            return "redirect:fruit.do?pageNo=" + pageNo;
        }catch (Exception e){
            e.printStackTrace();
            throw new FruitServiceException("控制层出问题了");
        }

    }
    private String index(String oper, String keyword,Integer pageNo, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (pageNo == null){
            pageNo = 1;
        }

        if (StringUtil.isNotEmpt(oper) && "search".equals(oper)){
            if (StringUtil.isEmpty(keyword)){
                keyword="";
            }
            //将keyword存放到session作用域中
            session.setAttribute("keyword",keyword);
        }else{
            Object keywordObj =  session.getAttribute("keyword");
            if (keywordObj != null){
                keyword = (String) keywordObj;
            }else{
                keyword = "";
            }
        }
        //将pageNo存放到作用域
        //重新更新当前页的值
        session.setAttribute("pageNo",pageNo);
        //获取指定页数上的记录
        List<Fruit> fruitList = fruitService.getFruitList(keyword,pageNo);
        //获取总页数
        int fruitCount = fruitService.getFruitCount(keyword);
        session.setAttribute("fruitList",fruitList);
        session.setAttribute("pageCount",fruitCount);
        return "index";
    }
}

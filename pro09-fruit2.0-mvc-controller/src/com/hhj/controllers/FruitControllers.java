package com.hhj.controllers;
import com.hhj.fruits.Fruit;
import com.hhj.fruits.dao.FruitDao;
import com.hhj.fruits.impl.FruitDaoImpl;
import com.hhj.myssm.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
public class FruitControllers {
    private FruitDao fruitDao = new FruitDaoImpl();
    private String update(Integer fid,String fname,Integer price, Integer fcount,String remark) {
        //3.执行更新
        fruitDao.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //4、资源跳转
        return "redirect:fruit.do";

    }
    //渲染edit.html的
    private String edit(Integer fid,HttpServletRequest request)  {
        //获取发过来的值
        if (fid !=null){
            Fruit fruit = fruitDao.getFruitByFid(fid);
            request.setAttribute("fruit",fruit); //将fruit对象保存在请求作用域下
            return "edit";
        }
        return "error";
    }
    private String del(Integer fid) {
        //获取到fid
        if (fid !=null){
            //从数据库中删除id
            fruitDao.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname, Integer price,Integer fcount,String remark,HttpServletRequest request)  {
        //添加到数据库中
        fruitDao.insertFruit(new Fruit(0,fname,price,fcount,remark));
        int pageNo = (Integer) request.getSession().getAttribute("pageNo");
        //response.sendRedirect("fruit.do?pageNo=" + pageNo);
        return "redirect:fruit.do?pageNo=" + pageNo;

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
        FruitDao fruitDao = new FruitDaoImpl();
        //将pageNo存放到作用域
        //重新更新当前页的值
        session.setAttribute("pageNo",pageNo);
        //获取指定页数上的记录
        List<Fruit> fruitList = fruitDao.getFruitList(keyword,pageNo);
        //获取总页数
        int fruitCount = fruitDao.getFruitCount(keyword);
        session.setAttribute("fruitList",fruitList);
        session.setAttribute("pageCount",(fruitCount+4)/5);
        return "index";
    }
}

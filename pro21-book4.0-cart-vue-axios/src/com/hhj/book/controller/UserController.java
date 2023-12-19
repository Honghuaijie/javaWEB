package com.hhj.book.controller;

import com.hhj.book.pojo.Book;
import com.hhj.book.pojo.User;
import com.hhj.book.service.BookService;
import com.hhj.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * ClassName: UserController
 * Package: com.hhj.book.comtroller
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/9 18:20
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class UserController {
    UserService userService = null;
    public String login(String uname, String pwd, HttpSession session){
        User user = userService.login(uname, pwd);
        if (user != null){
            session.setAttribute("currUser",user);
            return "redirect:book.do"; //默认调用bookcontroller中的index方法
        }
        //执行到这里说明没有找到用户，返回登陆页面
        return "user/login";
    }

    public String regist(String verifyCode, String uname, String pwd, String email, HttpSession session, HttpServletResponse response) throws IOException {
        Object kaptchaCodeobj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (kaptchaCodeobj == null || !verifyCode.equals(kaptchaCodeobj)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script language='javascript'> alert('验证码不正确！'); window.location.href='page.do?operate=page&page=user/regist';</script>");
            //return "user/regist";
            return null;
        }else if (verifyCode.equals(kaptchaCodeobj)){
            userService.regist(new User(uname,pwd,email));
            return "user/login";
        }
        return "user/login";
    }

    public String ckUname(String uname){
        User user = userService.getUser(uname);
        if (user != null){
            //用户名已经被占用
            return "json:{'uname': '1'}";
        }else{
            //用户名可以注册
            return "json:{'uname':'0'}";
        }
    }
}

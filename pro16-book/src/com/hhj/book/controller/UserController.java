package com.hhj.book.controller;

import com.hhj.book.pojo.User;
import com.hhj.book.service.UserService;

import javax.servlet.http.HttpSession;

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
        System.out.println(user);

        return "index";
    }
}

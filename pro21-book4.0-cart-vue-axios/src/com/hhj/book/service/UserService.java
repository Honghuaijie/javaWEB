package com.hhj.book.service;

import com.hhj.book.pojo.User;

/**
 * ClassName: UserService
 * Package: com.hhj.book.service
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/9 18:27
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface UserService {
    //通过用户名和密码获取用户信息
    User login(String uname, String pwd);

    //注册用户
    void regist(User user);

    //通过用户名来获取用户信息
    User getUser(String uname);
}

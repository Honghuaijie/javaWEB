package com.hhj.book.dao;

import com.hhj.book.pojo.User;

/**
* ClassName: UserDap
* Package: com.hhj.book.dao
* Description:
* @Author honghuaijie
* @Create 2023/11/9 18:21
* @Version 1.0
* Yesterday is history,tomorrow is a mystery,
* but today is a gift.That is why it's called the present
*/
public interface UserDao {
    //根据用户名和密码 回去用户信息
    User getUser(String uname, String pwd);

    //添加用户
    void addUser(User user );

    //根据用户名来获取用户
    User getUser(String uname);
}

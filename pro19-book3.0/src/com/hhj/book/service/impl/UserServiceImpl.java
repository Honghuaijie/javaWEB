package com.hhj.book.service.impl;

import com.hhj.book.dao.UserDao;
import com.hhj.book.pojo.User;
import com.hhj.book.service.UserService;

/**
 * ClassName: UserServiceImpl
 * Package: com.hhj.book.service.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/9 18:28
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = null;
    @Override
    public User login(String uname, String pwd) {
        return userDao.getUser(uname,pwd);
    }

    @Override
    public void regist(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUser(String uname) {
        return userDao.getUser(uname);
    }
}

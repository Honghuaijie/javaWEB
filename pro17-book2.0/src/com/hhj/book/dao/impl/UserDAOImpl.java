package com.hhj.book.dao.impl;

import com.hhj.book.dao.UserDao;
import com.hhj.book.pojo.User;
import com.hhj.myssm.basedao.BaseDao;

import java.util.List;

/**
 * ClassName: UserDAOImpl
 * Package: com.hhj.book.dao.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/9 18:23
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class UserDAOImpl implements UserDao {
    @Override
    public User getUser(String uname, String pwd) {
        String sql = "select * from t_user where uname = ? and pwd = ?";
        User user = null;
        try {
            List<User> users = BaseDao.executeQuery(User.class, sql, uname, pwd);
            if (users.size() >0){
                user = users.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

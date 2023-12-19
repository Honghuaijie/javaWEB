package come.hhj.qqzone.service.impl;

import come.hhj.qqzone.dao.UserBasicDao;
import come.hhj.qqzone.pojo.UserBasic;
import come.hhj.qqzone.service.UserBasicService;

import java.util.List;

/**
 * ClassName: UserBasicServiceImpl
 * Package: come.hhj.qqzone.service.impl
 * Description:
 *      用户 业务层实现类
 * @Author honghuaijie
 * @Create 2023/11/5 19:14
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class UserBasicServiceImpl implements UserBasicService {
    UserBasicDao userBasicDao = null;
    @Override
    public UserBasic login(String loginId, String pwd) {
        UserBasic userBasic = userBasicDao.getUserBasic(loginId, pwd);
        return userBasic;
    }

    //获取好友列表
    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        return userBasicDao.getUserBasicList(userBasic);
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDao.getUserBasicById(id);
    }


}

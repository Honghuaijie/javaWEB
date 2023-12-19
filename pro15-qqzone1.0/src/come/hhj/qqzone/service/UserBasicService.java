package come.hhj.qqzone.service;

import come.hhj.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicService
 * Package: come.hhj.qqzone.service
 * Description:
 *   userbasic 业务层接口
 * @Author honghuaijie
 * @Create 2023/11/5 19:10
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface UserBasicService {
    //登录
    UserBasic login(String loginId,String pwd);
    //获取好友列表
    List<UserBasic> getFriendList(UserBasic userBasic);
    //根据id 获取用户
    UserBasic getUserBasicById(Integer id);
}

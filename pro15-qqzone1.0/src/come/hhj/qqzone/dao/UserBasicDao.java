package come.hhj.qqzone.dao;

import come.hhj.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicDao
 * Package: come.hhj.qqzone.dao
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/5 16:41
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface UserBasicDao {
    //根据账户和密码获取特定用户信息
    public UserBasic getUserBasic(String loginId,String pwd);
    //获取指定用户的所有好友列表
    public List<UserBasic> getUserBasicList(UserBasic userBasic);
    //根据ID查询用户信息
    UserBasic getUserBasicById(Integer id);

}

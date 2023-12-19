package come.hhj.qqzone.dao;

import come.hhj.qqzone.pojo.Topic;
import come.hhj.qqzone.pojo.UserBasic;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: TopicDao
 * Package: come.hhj.qqzone.dao
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/5 16:44
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface TopicDao {
    //获取指定用户的所有日志列表
    public List<Topic> getTopicList(UserBasic userBasic);
    //添加日志
    public void addTopic(Topic topic);
    //删除日志
    void delTopic(Topic topic);
    //获取特定日志信息
    Topic getTopic(Integer id);
}

package come.hhj.qqzone.dao.imp;

import come.hhj.myssm.basedao.BaseDao;
import come.hhj.qqzone.dao.TopicDao;
import come.hhj.qqzone.pojo.Topic;
import come.hhj.qqzone.pojo.UserBasic;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: TopicDAOImp
 * Package: come.hhj.qqzone.dao.imp
 * Description:
 *  日志实现类
 * @Author honghuaijie
 * @Create 2023/11/5 18:50
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class TopicDAOImp implements TopicDao {
    //获取指定用户的所有日志列表
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        String sql = "SELECT * FROM t_topic WHERE author = ?;";
        List<Topic> topics = null;
        try {
            topics = BaseDao.executeQuery(Topic.class, sql, userBasic.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topics;
    }
    //添加日志
    @Override
    public void addTopic(Topic topic) {
        String sql = "insert into t_topic values(?,?,?,?,?);";
        try {
            BaseDao.executeUpdata(sql,0,topic.getTitle(),topic.getContent(),topic.getTopicDate(),topic.getAuthor().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //删除日志
    @Override
    public void delTopic(Topic topic) {
        String sql = "delete from t_topic where id = ?;";
        try {
            BaseDao.executeUpdata(sql,topic.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //获取特定日志信息
    @Override
    public Topic getTopic(Integer id) {
        String sql = "select * from t_topic where id = ?";
        Topic topic = null;
        try {
            List<Topic> topics = BaseDao.executeQuery(Topic.class, sql, id);
            if (topics.size()>0){
                topic = topics.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topic;
    }
}

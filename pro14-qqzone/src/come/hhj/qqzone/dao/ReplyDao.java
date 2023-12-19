package come.hhj.qqzone.dao;

import come.hhj.qqzone.pojo.Reply;
import come.hhj.qqzone.pojo.Topic;

import java.util.List;

/**
 * ClassName: ReplyDao
 * Package: come.hhj.qqzone.dao
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/5 16:48
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface ReplyDao {
    //获取指定日志的回复列表
    List<Reply> getReplyList(Topic topic);
    //添加回复
    void addReply(Reply reply);
    //删除回复
    void delReply(Integer id);


}

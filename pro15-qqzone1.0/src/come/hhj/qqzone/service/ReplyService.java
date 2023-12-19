package come.hhj.qqzone.service;

import come.hhj.qqzone.pojo.Reply;
import come.hhj.qqzone.pojo.Topic;

import java.util.List;

/**
 * ClassName: ReplyService
 * Package: come.hhj.qqzone.service
 * Description:
 *  回复信息   业务层 接口
 * @Author honghuaijie
 * @Create 2023/11/6 16:51
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface ReplyService {
    //获取某个日志的所有回复信息
    List<Reply> getReplyListByTopicId(Integer id);
    //添加回复信息
    void addReply(Reply reply);

    //删除回复信息
    void delReply(Reply reply);

    //获取回复信息，该回复信息中应该包含主人信息
    Reply getReplyById(Integer replyId);
}

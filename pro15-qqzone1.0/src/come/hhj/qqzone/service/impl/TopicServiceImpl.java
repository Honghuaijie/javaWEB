package come.hhj.qqzone.service.impl;

import come.hhj.qqzone.dao.TopicDao;
import come.hhj.qqzone.pojo.Reply;
import come.hhj.qqzone.pojo.Topic;
import come.hhj.qqzone.pojo.UserBasic;
import come.hhj.qqzone.service.ReplyService;
import come.hhj.qqzone.service.TopicService;

import java.util.List;

/**
 * ClassName: TopicServiceImpl
 * Package: come.hhj.qqzone.service.impl
 * Description:
 *  日志 业务层 实现类
 * @Author honghuaijie
 * @Create 2023/11/5 19:59
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao = null;
    //此处引用的是replyService，而不是replyDAO
    private ReplyService replyService = null;
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return  topicDao.getTopicList(userBasic);
    }

    //此处不仅需要获取topic本身的信息，还需要获取topic所对应的所有回复和主人回复
    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = topicDao.getTopic(id);
        //获取该topic所对应的所有回复信息
        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);
        return topic;
    }

    @Override
    public void addTopic(Topic topic) {
        topicDao.addTopic(topic);
    }

    @Override
    public void delTopic(Integer topicId) {
        topicDao.delTopic(new Topic(topicId));
    }


}

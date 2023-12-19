package come.hhj.qqzone.service.impl;

import come.hhj.qqzone.dao.TopicDao;
import come.hhj.qqzone.pojo.Topic;
import come.hhj.qqzone.pojo.UserBasic;
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
    TopicDao topicDao = null;
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return  topicDao.getTopicList(userBasic);
    }
}

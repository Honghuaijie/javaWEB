package come.hhj.qqzone.service;

import come.hhj.qqzone.pojo.Topic;
import come.hhj.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: TopicService
 * Package: come.hhj.qqzone.service
 * Description:
 *  日志 业务层 接口
 * @Author honghuaijie
 * @Create 2023/11/5 19:57
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface TopicService {
    //查询特定用户的日志
    List<Topic> getTopicList(UserBasic userBasic);
}

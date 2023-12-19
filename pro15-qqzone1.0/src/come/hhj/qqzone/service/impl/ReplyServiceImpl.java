package come.hhj.qqzone.service.impl;

import come.hhj.qqzone.dao.HostReplyDao;
import come.hhj.qqzone.dao.ReplyDao;
import come.hhj.qqzone.pojo.HostReply;
import come.hhj.qqzone.pojo.Reply;
import come.hhj.qqzone.pojo.Topic;
import come.hhj.qqzone.pojo.UserBasic;
import come.hhj.qqzone.service.HostReplyService;
import come.hhj.qqzone.service.ReplyService;
import come.hhj.qqzone.service.UserBasicService;

import java.util.List;

/**
 * ClassName: ReplyServiceImpl
 * Package: come.hhj.qqzone.service.impl
 * Description:
 *  回复信息 业务层
 * @Author honghuaijie
 * @Create 2023/11/6 16:53
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class ReplyServiceImpl implements ReplyService {
    ReplyDao replyDao = null;
    //此处引入的是其他POJO对应的service接口，而不是DAO接口
    //其他POJO对应的业务逻辑是封装在service层的，
    // 我需要调用别人的业务逻辑方法，而不要去深入考虑人家内部的细节
    //理解：别人的业务层可能包含了多个DAO方法，我们直接调用业务就行了，而不用考虑DAO层
    HostReplyService hostReplyService = null;
    //根据topic的id获取关联的所有的回复 该回复中包含了主人回复
    //根据topic里面author的id，获取

    //使用用户业务层，去获取用户相关信息
    UserBasicService userBasicService = null ;
    @Override
    public List<Reply> getReplyListByTopicId(Integer id) {
        List<Reply> replyList = replyDao.getReplyList(new Topic(id));
        //回复中包含了主人回复，所以需要循环遍历，找出所有的主人回复
        for (Reply reply : replyList){
            //如果该回复中包含了主人回复，就存放到reply中
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
            //获取该回复者的信息 userbasic(通过user的id获取该信息）
            UserBasic replyUser = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(replyUser);
        }
        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDao.addReply(reply);
    }


    //根据传入的replyId去删除回复信息
    @Override
    public void delReply(Reply reply) {
        //首先判断该回复信息是否包含主人信息
        if (reply.getHostReply() !=null){
            //删除其主人回复信息
            hostReplyService.delHostReply(reply.getHostReply().getId());
        }
        //删除回复信息
        replyDao.delReply(reply.getId());
    }

    @Override
    public Reply getReplyById(Integer replyId) {
        //1.先获取该回复的主人回复，没有就为null
        HostReply hostReply = hostReplyService.getHostReplyByReplyId(replyId);
        //2.获取该回复
        Reply reply = replyDao.getReply(replyId);
        //将该回复的主人回复放入到reply中，并返回
        reply.setHostReply(hostReply);

        return reply;
    }
}

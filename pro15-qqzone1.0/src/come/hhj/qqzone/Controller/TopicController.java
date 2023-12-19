package come.hhj.qqzone.Controller;

import come.hhj.qqzone.pojo.Reply;
import come.hhj.qqzone.pojo.Topic;
import come.hhj.qqzone.pojo.UserBasic;
import come.hhj.qqzone.service.ReplyService;
import come.hhj.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * ClassName: TopicController
 * Package: come.hhj.qqzone.Controller
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/6 16:33
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class TopicController {
    TopicService topicService = null;
    ReplyService replyService = null;

    //传入一个日志id，要求获取该日志
    public String topicDetail(Integer id, HttpSession session){
        //获取当前日志,在getTopicById方法中，我们获取了该日志的所有回复信息和主人回复信息
        Topic topic = topicService.getTopicById(id);
        //将当前日志保存到session中，方便在detail.html中获取他的回复信息
        session.setAttribute("topic",topic);

        return "frames/detail";
    }

    //用于重定向
    public String reNewTopicDetail(HttpSession session){
        //通过原来的session中的topic来获取id
        Topic topicObj =(Topic) session.getAttribute("topic");
        //获取当前日志,在getTopicById方法中，我们获取了该日志的所有回复信息和主人回复信息
        Topic topic = topicService.getTopicById(topicObj.getId());
        //将当前日志保存到session中，方便在detail.html中获取他的回复信息
        session.setAttribute("topic",topic);
        return "frames/detail";
    }

    public String addTopic(String title,String content,HttpSession session){
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");

        Topic topic = new Topic(title, content, new Date(), userBasic);
        topicService.addTopic(topic);
        //添加topic后，数据库中的topic已经变了，但是session中的topic没有变，我们应该修改session中的topic
        reNewFriend(session);
        return "index";
    }

    //删除日志需要先删除所有的回复
    public String delTopic(Integer topicId,HttpSession session){
        //获取该topic的所有回复
        List<Reply> replyListByTopicId = replyService.getReplyListByTopicId(topicId);

        //依次删除所有的reply
        for (Reply reply: replyListByTopicId){
            replyService.delReply(reply);
        }
        topicService.delTopic(topicId);
        reNewFriend(session);
        return "frames/main";
    }

    //重新渲染session中的friend的信息
    private void reNewFriend(HttpSession session){
        UserBasic friend = (UserBasic) session.getAttribute("friend");
        List<Topic> topicList = topicService.getTopicList(friend);
        friend.setTopicList(topicList);
        session.setAttribute("friend",friend);
    }

}

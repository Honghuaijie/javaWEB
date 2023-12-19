package come.hhj.qqzone.Controller;

import come.hhj.qqzone.pojo.Reply;
import come.hhj.qqzone.pojo.Topic;
import come.hhj.qqzone.pojo.UserBasic;
import come.hhj.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * ClassName: ReplyController
 * Package: come.hhj.qqzone.Controller
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/7 15:07
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class ReplyController {
    private ReplyService replyService;
    public String addReply(String content ,Integer topicId , HttpSession session){
        System.out.println("addreply");
        //获取当前回复的作者，也就是登录者
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");

        Reply reply = new Reply(content,new Date(),userBasic,new Topic(topicId));
        replyService.addReply(reply);

        //重定向到topicDetail
        return "redirect:topic.do?operate=topicDetail&id=" + topicId ;
    }
    //删除回复信息
    public String delReply(Integer replyId,Integer topicId){
        Reply reply = replyService.getReplyById(replyId);
        replyService.delReply(reply);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId ;
    }
}

package come.hhj.qqzone.Controller;

import come.hhj.qqzone.pojo.HostReply;
import come.hhj.qqzone.pojo.Reply;
import come.hhj.qqzone.pojo.UserBasic;
import come.hhj.qqzone.service.HostReplyService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * ClassName: HostReplyController
 * Package: come.hhj.qqzone.Controller
 * Description:
 *  主人回复业务层，实现类
 * @Author honghuaijie
 * @Create 2023/11/7 15:56
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class HostReplyController {
    private HostReplyService hostReplyService = null;
    public String addHostReply(String content, Integer replyId, Integer topicId,HttpSession session){
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        HostReply hostReply = new HostReply(content,new Date(),userBasic,new Reply(replyId));
        hostReplyService.addHostReply(hostReply);
        //重定向到topicDetail
        return "redirect:topic.do?operate=topicDetail&id=" +  topicId;
    }

    //传入主人回复信息的ID，和当前日志的ID
    public String delHostReply(Integer hostReplyId){
        hostReplyService.delHostReply(hostReplyId);
        return "redirect:topic.do?operate=reNewTopicDetail";
    }
}

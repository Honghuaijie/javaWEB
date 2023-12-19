package come.hhj.qqzone.Controller;

import come.hhj.qqzone.pojo.Topic;
import come.hhj.qqzone.pojo.UserBasic;
import come.hhj.qqzone.service.UserBasicService;
import come.hhj.qqzone.service.impl.TopicServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: UserController
 * Package: come.hhj.qqzone.Controller
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/5 20:04
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class UserController {
    private UserBasicService userBasicService = null;
    private TopicServiceImpl topicService = null;
    public String login(String loginId, String pwd, HttpSession session){
        //登陆验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic != null){
            //通过这个用户查询到他的好友列表
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            //得到该用户的日志列表
            List<Topic> topicList  = topicService.getTopicList(userBasic);
            //将好友列表和日志列表放入到userBasic中
            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);
            //把userBasic保存的是登录者的信息
            session.setAttribute("userBasic",userBasic);
            //friend保存的是当前进入的是谁的空间
            session.setAttribute("friend",userBasic);
            return "index"; //跳转到index.html页面
        }else{
            return "login"; //如果userBasic为空，说明没有该用户，重新跳转到login页面
        }

    }

    /**
     *  跳转空间
     * @param id 是好友的id
     * @param session 需要修改session中的值
     * @return
     */
    public String friend(Integer id,HttpSession session){
        //根据id获取该好友的信息
        UserBasic currFriend = userBasicService.getUserBasicById(id);
        //获取该好友的日志信息
        List<Topic> topicList  = topicService.getTopicList(currFriend);
        //将日志信息保存到好友信息中
        currFriend.setTopicList(topicList);
        //修改session作用域中的friend
        session.setAttribute("friend",currFriend);
        //渲染index页面
        return "index";
    }
}

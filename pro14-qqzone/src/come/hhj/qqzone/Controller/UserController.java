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
            //把userBasic保存到session作用域中，方便获取在html页面上获取
            session.setAttribute("userBasic",userBasic);
            return "index"; //跳转到index.html页面
        }else{
            return "login"; //如果userBasic为空，说明没有该用户，重新跳转到login页面
        }

    }
}

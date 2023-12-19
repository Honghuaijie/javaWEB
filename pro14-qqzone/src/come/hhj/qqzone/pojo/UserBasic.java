package come.hhj.qqzone.pojo;

import java.util.List;

/**
 * ClassName: UserBasic
 * Package: come.hhj.qqzone.pojo
 * Description:
 *      用户基本信息表
 * @Author honghuaijie
 * @Create 2023/11/5 15:43
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class UserBasic {
    private Integer id;
    private String loginId;
    private String nickName;
    private String pwd;
    private String headImg;

    //与其他表之间的关联
    private UserDetail userDetail; //  用户细节表 1:1
    private List<Topic> topicList; // 日志表 1:n
    private List<UserBasic> friendList; // 好友表 n:n

    public UserBasic(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<UserBasic> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserBasic> friendList) {
        this.friendList = friendList;
    }

    public UserBasic(){

    }


}

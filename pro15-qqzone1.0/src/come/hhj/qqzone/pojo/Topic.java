package come.hhj.qqzone.pojo;

import java.util.Date;
import java.util.List;

/**
 * ClassName: Topic
 * Package: come.hhj.qqzone.pojo
 * Description:
 *  日志表
 * @Author honghuaijie
 * @Create 2023/11/5 15:43
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class Topic {
    private Integer id;
    private String title;
    private String content;
    private Date topicDate;
    private UserBasic author;  //m:1

    //与其他表之间的联系
    private List<Reply> replyList; //1:n


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(Date topicDate) {
        this.topicDate = topicDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public Topic(){

    }
    public Topic(Integer id){
        this.id = id;
    }

    public Topic(String title, String content, Date topicDate, UserBasic author) {
        this.title = title;
        this.content = content;
        this.topicDate = topicDate;
        this.author = author;
    }
}

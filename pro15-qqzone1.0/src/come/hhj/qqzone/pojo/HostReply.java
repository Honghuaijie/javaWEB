package come.hhj.qqzone.pojo;

import java.util.Date;

/**
 * ClassName: HostReply
 * Package: come.hhj.qqzone.pojo
 * Description:
 *      主人回复表
 * @Author honghuaijie
 * @Create 2023/11/5 15:43
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class HostReply {
    private Integer id;
    private String content;
    private Date hostReplyDate;
    private UserBasic author; //M:1
    private Reply reply; //1:1


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getHostReplyDate() {
        return hostReplyDate;
    }

    public void setHostReplyDate(Date hostReplyDate) {
        this.hostReplyDate = hostReplyDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public HostReply(){

    }

    public HostReply(String content, Date hostReplyDate, UserBasic author, Reply reply) {
        this.content = content;
        this.hostReplyDate = hostReplyDate;
        this.author = author;
        this.reply = reply;
    }
}

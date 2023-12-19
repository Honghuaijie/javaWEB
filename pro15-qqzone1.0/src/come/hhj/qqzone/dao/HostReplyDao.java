package come.hhj.qqzone.dao;

import come.hhj.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyDao
 * Package: come.hhj.qqzone.dao
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/5 16:51
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface HostReplyDao {
    //获取回复ID 获取主人回复信息
    HostReply getHostReplyByReplyId(Integer replyId);
    //添加主人回复信息
    void addHostReply(HostReply hostReply);
    //删除主人回复信息
    void delHostReply(Integer hostReplyId);

    //根据replyId来删除主人回复信息
    void delHostReplyByReplyId(Integer replyId);
}

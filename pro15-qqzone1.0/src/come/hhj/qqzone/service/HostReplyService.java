package come.hhj.qqzone.service;

import come.hhj.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyService
 * Package: come.hhj.qqzone.service
 * Description:
 *  主人回复信息 业务层接口
 * @Author honghuaijie
 * @Create 2023/11/6 17:08
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface HostReplyService {
    //根据回复的id获取主人回复信息
    HostReply getHostReplyByReplyId(Integer replyId);

    //添加主人回复信息
    void addHostReply(HostReply hostReply);

    //根据id删除主人回复信息
    void delHostReply(Integer hostReplyId);

    //根据reply来删除主人回复信息
    void delHostReplyByReplyId(Integer replyId);
}

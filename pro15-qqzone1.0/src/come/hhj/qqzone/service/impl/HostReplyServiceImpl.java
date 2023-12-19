package come.hhj.qqzone.service.impl;

import come.hhj.qqzone.dao.HostReplyDao;
import come.hhj.qqzone.pojo.HostReply;
import come.hhj.qqzone.service.HostReplyService;

/**
 * ClassName: HostReplyServiceImpl
 * Package: come.hhj.qqzone.service.impl
 * Description:
 *  主人回复  业务层实现类
 * @Author honghuaijie
 * @Create 2023/11/6 17:09
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class HostReplyServiceImpl implements HostReplyService {
    HostReplyDao hostReplyDao = null;
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDao.getHostReplyByReplyId(replyId);
    }

    @Override
    public void addHostReply(HostReply hostReply) {
        hostReplyDao.addHostReply(hostReply);
    }

    //根据id来删除主人回复信息
    @Override
    public void delHostReply(Integer hostReplyId) {
        hostReplyDao.delHostReply(hostReplyId);
    }

    //根据replyId来删除主人回复信息
    @Override
    public void delHostReplyByReplyId(Integer replyId) {
        hostReplyDao.delHostReplyByReplyId(replyId);
    }
}

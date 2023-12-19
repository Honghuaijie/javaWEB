package come.hhj.qqzone.dao.imp;

import come.hhj.myssm.basedao.BaseDao;
import come.hhj.qqzone.dao.HostReplyDao;
import come.hhj.qqzone.pojo.HostReply;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * ClassName: HostReplyDaoImp
 * Package: come.hhj.qqzone.dao.imp
 * Description:
 *      HostReplyDao 的实现类
 * @Author honghuaijie
 * @Create 2023/11/6 17:10
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class HostReplyDaoImp implements HostReplyDao {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        String sql = "select * from t_host_reply where reply = ?";
        HostReply hostReply = null;
        try {
            List<HostReply> hostReplyList = BaseDao.executeQuery(HostReply.class, sql, replyId);
            if (hostReplyList.size() >0){
                hostReply = hostReplyList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostReply;
    }

    @Override
    public void addHostReply(HostReply hostReply) {
        String sql = "insert into t_host_reply values(?,?,?,?,?)";
        try {
            BaseDao.executeUpdata(sql,0,hostReply.getContent(),hostReply.getHostReplyDate(),hostReply.getAuthor().getId(),hostReply.getReply().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delHostReply(Integer hostReplyId) {
        String sql = "delete from t_host_reply where id = ?";
        try {
            BaseDao.executeUpdata(sql,hostReplyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delHostReplyByReplyId(Integer replyId) {
        String sql = "delete from t_host_reply where reply = ?";
        try {
            BaseDao.executeUpdata(sql,replyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

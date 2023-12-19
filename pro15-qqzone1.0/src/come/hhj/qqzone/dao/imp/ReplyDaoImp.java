package come.hhj.qqzone.dao.imp;

import come.hhj.myssm.basedao.BaseDao;
import come.hhj.qqzone.dao.ReplyDao;
import come.hhj.qqzone.pojo.Reply;
import come.hhj.qqzone.pojo.Topic;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * ClassName: ReplyDaoImp
 * Package: come.hhj.qqzone.dao.imp
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/6 16:54
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class ReplyDaoImp implements ReplyDao {

    //通过日志，获取所有的回复信息
    @Override
    public List<Reply> getReplyList(Topic topic) {
        String sql = "select * from t_reply where topic = ?";
        List<Reply> replyList = null;
        try {
            replyList = BaseDao.executeQuery(Reply.class,sql,topic.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return replyList;
    }

    //添加日志
    @Override
    public void addReply(Reply reply) {
        String sql = "insert into t_reply values(?,?,?,?,?)";
        try {
            BaseDao.executeUpdata(sql,0,reply.getContent(),reply.getReplyDate(),reply.getAuthor().getId(),reply.getTopic().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delReply(Integer id) {
        String sql = "delete from t_reply where id = ?";
        try {
            BaseDao.executeUpdata(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reply getReply(Integer id) {
        String sql = "select * from t_reply where id = ?";
        Reply reply = null;
        try {
            List<Reply> replyList = BaseDao.executeQuery(Reply.class, sql, id);
            if (replyList.size() >0){
                reply = replyList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}

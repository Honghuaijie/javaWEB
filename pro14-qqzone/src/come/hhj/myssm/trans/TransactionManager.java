package come.hhj.myssm.trans;

import come.hhj.myssm.basedao.JdbcUtilsV2;

import java.sql.SQLException;

/**
 * ClassName: TransactionManager
 * Package: com.hhj.trans
 * Description:
 *      用来开启事务、回滚事务、提交事务
 * @Author honghuaijie
 * @Create 2023/11/4 16:19
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class TransactionManager {
    //开启事务
    public static void beginTrans() throws SQLException {
        JdbcUtilsV2.getConnection().setAutoCommit(false);
    }

    //提交事务
    public static void commit() throws SQLException {
        JdbcUtilsV2.getConnection().commit();
        JdbcUtilsV2.freeConnection();
    }
    //回滚事务
    public static void rollback() throws SQLException {
        JdbcUtilsV2.getConnection().rollback();
        JdbcUtilsV2.freeConnection();

    }



}

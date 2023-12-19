package come.hhj.qqzone.dao.imp;

import come.hhj.myssm.basedao.BaseDao;
import come.hhj.qqzone.dao.UserBasicDao;
import come.hhj.qqzone.pojo.UserBasic;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static javax.swing.UIManager.get;

/**
 * ClassName: UserBasicDAOImp
 * Package: come.hhj.qqzone.dao.imp
 * Description:
 *      user_basic 表的 DAO层，对哪个表进行操作，就将操作写在哪个表的DAO层上
 * @Author honghuaijie
 * @Create 2023/11/5 16:52
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class UserBasicDAOImp implements UserBasicDao {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        String sql = "select * from t_user_basic where loginId = ? and pwd = ?;";
        UserBasic userBasic = null;

        try {
            List<UserBasic> userBasics = BaseDao.executeQuery(UserBasic.class,sql, loginId, pwd);
            if (userBasics.size() !=0){
                userBasic = userBasics.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return userBasic;

    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "SELECT * FROM t_user_basic WHERE id IN (SELECT fid FROM t_friend WHERE uid = ?);";
        List<UserBasic> userBasics = null;

        try {
            userBasics = BaseDao.executeQuery(UserBasic.class, sql, userBasic.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBasics;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        String sql = "select * from t_user_basic where id = ?";
        UserBasic userBasics = null;
        try {
            List<UserBasic> userBasicsList = BaseDao.executeQuery(UserBasic.class, sql, id);
            if (userBasicsList.size() >0){
                userBasics = userBasicsList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBasics;
    }
}

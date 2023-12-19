package com.hhj.book.dao.impl;

import com.hhj.book.dao.OrderDao;
import com.hhj.book.pojo.OrderBean;
import com.hhj.book.pojo.User;
import com.hhj.myssm.basedao.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * ClassName: OrderDaoImple
 * Package: com.hhj.book.dao.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/10 18:54
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public void addOrder(OrderBean order) {
        String sql = "insert into t_order values(0,?,?,?,?,?);";
        try {
            BaseDao.executeUpdata(sql,order.getOrderNo(),order.getOrderDate(),order.getOrderUser().getId(),order.getOrderMoney(),order.getOrderStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getIdByOrderNo(OrderBean order) {
        String sql = "select * from t_order where orderNo = ?;";
        int id = 0;
        try {
            List<OrderBean> orderBeans = BaseDao.executeQuery(OrderBean.class, sql, order.getOrderNo());
            if (orderBeans.size() > 0){
                id = orderBeans.get(0).getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 获取该用户的所有订单
     * @param user
     * @return
     */
    @Override
    public List<OrderBean> getOrderByUserId(User user) {
        String sql = "select * from t_order where orderUser = ?;";
        List<OrderBean> orderBeanList = null;
        try {
            orderBeanList = BaseDao.executeQuery(OrderBean.class,sql,user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderBeanList;
    }
}

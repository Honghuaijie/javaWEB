package com.hhj.book.dao.impl;

import com.hhj.book.dao.OrderItemDao;
import com.hhj.book.pojo.OrderBean;
import com.hhj.book.pojo.OrderItem;
import com.hhj.myssm.basedao.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * ClassName: OrderItemDaoimp
 * Package: com.hhj.book.dao.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/10 19:00
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class OrderItemDaoimp implements OrderItemDao {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item values(0,?,?,?);";
        try {
            BaseDao.executeUpdata(sql,orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> getOrderItemListByOrderId(OrderBean orderBean) {
        String sql = "select * from t_order_item where orderBean = ?";
        List<OrderItem> orderItemList = null;
        try {
             orderItemList = BaseDao.executeQuery(OrderItem.class, sql, orderBean.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

}

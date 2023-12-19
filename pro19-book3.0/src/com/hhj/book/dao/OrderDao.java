package com.hhj.book.dao;

import com.hhj.book.pojo.OrderBean;
import com.hhj.book.pojo.User;

import java.util.List;

/**
 * ClassName: OrderDao
 * Package: com.hhj.book.dao
 * Description:
 *  订单表  DAO 接口
 * @Author honghuaijie
 * @Create 2023/11/10 18:50
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface OrderDao {
    //添加一条order
    void addOrder(OrderBean order);

    //已订单号来查询order的id
    int getIdByOrderNo(OrderBean order);

    //查询用户的所有订单
    List<OrderBean> getOrderByUserId(User user);

}

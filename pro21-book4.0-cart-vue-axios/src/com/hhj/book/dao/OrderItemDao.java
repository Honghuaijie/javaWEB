package com.hhj.book.dao;

import com.hhj.book.pojo.OrderBean;
import com.hhj.book.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderItemDao
 * Package: com.hhj.book.dao
 * Description:
 *      订单详情表 DAO层
 * @Author honghuaijie
 * @Create 2023/11/10 18:52
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface OrderItemDao {
    void addOrderItem(OrderItem orderItem);
    //通过订单ID获取订单详情列表
    List<OrderItem> getOrderItemListByOrderId(OrderBean orderBean);
}

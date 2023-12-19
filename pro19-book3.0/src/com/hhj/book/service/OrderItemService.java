package com.hhj.book.service;

import com.hhj.book.pojo.OrderBean;
import com.hhj.book.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderItemService
 * Package: com.hhj.book.service
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/10 19:11
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface OrderItemService {
    //添加订单详情
    void addOrderItem(OrderItem orderItem);

    //根据订单ID来获取订单详情列表
    List<OrderItem> getOrderItemListByOrderId(OrderBean orderBean);
}

package com.hhj.book.service.impl;

import com.hhj.book.dao.OrderItemDao;
import com.hhj.book.pojo.OrderBean;
import com.hhj.book.pojo.OrderItem;
import com.hhj.book.service.OrderItemService;

import java.util.List;

/**
 * ClassName: OrderItemServiceImpl
 * Package: com.hhj.book.service.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/10 19:11
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class OrderItemServiceImpl implements OrderItemService {
    OrderItemDao orderItemDao = null;
    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemDao.addOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemListByOrderId(OrderBean orderBean) {
        return orderItemDao.getOrderItemListByOrderId(orderBean);
    }
}

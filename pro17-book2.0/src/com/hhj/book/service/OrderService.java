package com.hhj.book.service;

import com.hhj.book.pojo.OrderBean;
import com.hhj.book.pojo.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * ClassName: OrderBeanService
 * Package: com.hhj.book.service
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/10 19:04
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface OrderService {
    //结账：提交订单

    /**
     * 传入一个订单类
     * @param orderBean 传入一个订单类
     */
    void addOrderBean(OrderBean orderBean);

    //根据订单号获取订单ID
    int getIdByOrderNo(OrderBean orderBean);

    List<OrderBean> getOrderListByUserId(User user);
}

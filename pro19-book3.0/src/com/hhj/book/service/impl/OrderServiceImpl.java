package com.hhj.book.service.impl;

import com.hhj.book.dao.OrderDao;
import com.hhj.book.pojo.CartItem;
import com.hhj.book.pojo.OrderBean;
import com.hhj.book.pojo.OrderItem;
import com.hhj.book.pojo.User;
import com.hhj.book.service.CartService;
import com.hhj.book.service.OrderItemService;
import com.hhj.book.service.OrderService;

import java.util.List;

/**
 * ClassName: OrderServiceImp
 * Package: com.hhj.book.service.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/10 19:08
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = null;
    OrderItemService orderItemService = null;
    CartService cartService = null;
    @Override
    public void addOrderBean(OrderBean orderBean) {
        //1.添加订单
        orderDao.addOrder(orderBean);
        //获取刚刚添加的订单ID
        int orderId = orderDao.getIdByOrderNo(orderBean);
        //获取购物车项
        List<CartItem> cartItemList = orderBean.getOrderUser().getCartItemList();
        //2.添加订单详情(遍历购物车项)
        for (CartItem cartItem: cartItemList){
            orderItemService.addOrderItem(new OrderItem(cartItem.getBook(),cartItem.getBuyCount(),new OrderBean(orderId)));
        }
        //3.清空购物车表(在user中可以有购物车表)
        for (CartItem cartItem : cartItemList){
            cartService.deleteCartItem(cartItem);
        }

    }

    @Override
    public int getIdByOrderNo(OrderBean orderBean) {
        return orderDao.getIdByOrderNo(orderBean);
    }

    /**
     * 通过用户的id获取该用户的订单，以及所有订单的订单详情
     * @param user
     * @return
     */
    @Override
    public List<OrderBean> getOrderListByUserId(User user) {
        //先获取订单
        List<OrderBean> orderList = orderDao.getOrderByUserId(user);

        for (OrderBean order : orderList){
            //通过订单ID获取所有的订单详情
            List<OrderItem> orderItemList = orderItemService.getOrderItemListByOrderId(order);
            order.setOrderItemList(orderItemList); //将订单详情列表存入order中
            order.setOrderItemTotal(orderItemList.size()); //将订单详情列表的数量存入order中
        }
        return orderList;
    }
}

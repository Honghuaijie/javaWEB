package com.hhj.book.controller;

import com.hhj.book.pojo.CartItem;
import com.hhj.book.pojo.OrderBean;
import com.hhj.book.pojo.OrderItem;
import com.hhj.book.pojo.User;
import com.hhj.book.service.OrderService;
import com.hhj.book.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ClassName: OrderController
 * Package: com.hhj.book.controller
 * Description:
 * 订单 控制层
 * @Author honghuaijie
 * @Create 2023/11/10 19:18
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class OrderController {
    OrderService orderService = null;

    /**
     * 提交订单
     *
     * @param session
     * @return
     */
    public String checkout(HttpSession session){
        //获取用户(当前用户中的cartItemList为空)
        User currUser = (User) session.getAttribute("currUser");
        //获取总金额
        Double totalPrice = (Double) session.getAttribute("totalPrice");
        //获取购物车列表
        List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");
        //将购物车列表放入到用户中
        currUser.setCartItemList(cartList);
        //先创建一个基本的orderBean
        OrderBean orderBean = new OrderBean(UUID.randomUUID().toString(), new Date(), currUser, totalPrice, 0);
        //提交账单
        orderService.addOrderBean(orderBean);
        //将订单号保存到session中
        session.setAttribute("orderNo",orderBean.getOrderNo());
        return "cart/checkout";
    }

    /**
     * 显示当前用户的订单信息
     * @return
     */
    public String index(HttpSession session){
        //获取当前用户
        User currUser = (User) session.getAttribute("currUser");
        //根据用户获取订单列表
        List<OrderBean> orderList = orderService.getOrderListByUserId(currUser);
        //将订单列表放入到session中
        session.setAttribute("orderList",orderList);
        return "order/order";
    }




}

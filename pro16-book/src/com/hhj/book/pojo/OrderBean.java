package com.hhj.book.pojo;

import java.util.Date;
import java.util.List;

/**
 * ClassName: OrderBean
 * Package: com.hhj.book.pojo
 * Description:
 *      订单项
 * @Author honghuaijie
 * @Create 2023/11/9 9:21
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class OrderBean {
    private Integer id;
    private String orderNo;
    private Date orderDate;
    private User orderUser;
    private Double orderMoney;
    private Integer orderStatus;

    //一个订单包含多个订单详情
    private List<OrderItem> orderItemList;  //1:n

    public OrderBean(Integer id) {
        this.id = id;
    }


    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}

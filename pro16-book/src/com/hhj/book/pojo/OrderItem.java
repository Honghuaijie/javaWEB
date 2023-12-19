package com.hhj.book.pojo;

/**
 * ClassName: OrderItem
 * Package: com.hhj.book.pojo
 * Description:
 *      订单详情项
 * @Author honghuaijie
 * @Create 2023/11/9 9:24
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class OrderItem {
    private Integer id;
    private Book book;
    private Integer buyCouny;
    private OrderBean orderBean;  //M:1


    public OrderItem(){

    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public OrderItem(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCouny() {
        return buyCouny;
    }

    public void setBuyCouny(Integer buyCouny) {
        this.buyCouny = buyCouny;
    }
}

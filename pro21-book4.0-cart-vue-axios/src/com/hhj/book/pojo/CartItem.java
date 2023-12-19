package com.hhj.book.pojo;

import java.math.BigDecimal;

/**
 * ClassName: CartItem
 * Package: com.hhj.book.pojo
 * Description:
 *      购物车项
 * @Author honghuaijie
 * @Create 2023/11/9 9:30
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userBean;  //所属的用户

    private Double allPrice ; //用来记录小计

    //记录该购物项所在的购物车的购物项数量以及总金额
    private Double totalPrice;
    private Integer cartCount;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public User getUserBean() {
        return userBean;
    }


    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }


    public Double getAllPrice() {
        BigDecimal bigDecimalPrice = new BigDecimal("" + getBook().getPrice());
        BigDecimal bigDecimalBuyCount = new BigDecimal("" + buyCount);
        BigDecimal bigDecimalAllPrice = bigDecimalPrice.multiply(bigDecimalBuyCount);
        allPrice = bigDecimalAllPrice.doubleValue();
        return allPrice;
    }


    public CartItem(Integer id, Book book, Integer buyCount, User userBean) {
        this.id = id;
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public CartItem(){

    }



    public CartItem(Integer id) {
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

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUser() {
        return userBean;
    }

    public void setUser(User userBean) {
        this.userBean = userBean;
    }
}

package com.hhj.book.pojo;

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
    private User user;  //所属的用户

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
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

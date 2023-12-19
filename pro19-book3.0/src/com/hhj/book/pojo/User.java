package com.hhj.book.pojo;

import java.util.List;

/**
 * ClassName: User
 * Package: com.hhj.book.pojo
 * Description:
 *      用户类
 * @Author honghuaijie
 * @Create 2023/11/8 20:25
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class User {
    private Integer id;
    private String uname;
    private String pwd;
    private String email;
    private Integer role;

    //一个用户可以有多个购物车项
    private List<CartItem> cartItemList;

    public User(){

    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String uname, String pwd, String email) {
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}

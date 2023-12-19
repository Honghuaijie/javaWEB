package com.hhj.book.service;

import com.hhj.book.pojo.Book;
import com.hhj.book.pojo.CartItem;
import com.hhj.book.pojo.User;

import java.util.List;

/**
 * ClassName: CartService
 * Package: com.hhj.book.service
 * Description:
 *  购物车 业务层 接口
 * @Author honghuaijie
 * @Create 2023/11/10 9:02
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface CartService {
    int getCartCount(User user);

    void addCartCount(CartItem cartItem);
    //获取指定用户所有的购物车项
    List<CartItem> getCartList(User user);

    /**
     * 判断该图书是否在该用户的购物车中
     * @param book
     * @param user
     * @return
     */
    CartItem isInCarts(Book book, User user);

    //修改具体购物车的购买数量
    void updateCartBuyCounyById(CartItem cartItem, Integer buyCount);

    //传入一个购物车列表，返回所有的金额
    double getTotalPrice(List<CartItem> cartList);

    //删除一个购物项
    void deleteCartItem(CartItem cartItem);
}

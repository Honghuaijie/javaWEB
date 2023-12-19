package com.hhj.book.dao;

import com.hhj.book.pojo.CartItem;

import java.util.List;

/**
 * ClassName: CartDao
 * Package: com.hhj.book.dao
 * Description:
 * 购物车 DAO层
 * @Author honghuaijie
 * @Create 2023/11/10 8:56
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface CartDao {
    //获取当前用户的购物车总数
    int getCartCounts(int userId);

    //添加指定图书到购物车
    void addCart(CartItem cartItem);

    //获取到所的购物车项
    List<CartItem> getCartList(int userId);

    //修改具体购物车项的buyCount
    void updateCartBuyCountById(int id, int buyCount);

    //得到某用户的某图书的购物车项
    CartItem isInCart(int bookId, int userId);

    //删除某个购物项
    void delCartItem(CartItem cartItem);
}

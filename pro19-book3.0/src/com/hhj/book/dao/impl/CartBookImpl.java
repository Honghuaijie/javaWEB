package com.hhj.book.dao.impl;

import com.hhj.book.dao.CartDao;
import com.hhj.book.pojo.CartItem;
import com.hhj.myssm.basedao.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * ClassName: CartBookImpl
 * Package: com.hhj.book.dao.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/10 8:59
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class CartBookImpl implements CartDao {
    @Override
    public int getCartCounts(int userId) {
        String sql = "select count(*) from t_cart_item where userBean=?";
        int count = 0;
        try {
            count = BaseDao.queryCount(sql,userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void addCart(CartItem cartItem) {
        String sql = "insert into t_cart_item values(?,?,?,?);";
        try {
            BaseDao.executeUpdata(sql,0,cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUser().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItem> getCartList(int userId) {
        String sql = "select * from t_cart_item where userBean=?";
        List<CartItem> cartItemList = null;
        try {
            cartItemList = BaseDao.executeQuery(CartItem.class,sql,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItemList;
    }

    @Override
    public void updateCartBuyCountById(int id, int buyCount) {
        String sql = "update t_cart_item set buyCount = ? where id = ?";
        try {
            BaseDao.executeUpdata(sql,buyCount,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断某个商品是否在某个用户的购物车中
     * @param bookId  图书ID
     * @param userId  用户ID
     * @return  如果在购物车就返回该订单项，不在就返回null
     */
    @Override
    public CartItem isInCart(int bookId, int userId) {
        String sql = "select * from t_cart_item where book=? and userBean = ?";
        CartItem cartItem = null;
        try {
            List<CartItem> cartItemList = BaseDao.executeQuery(CartItem.class, sql, bookId, userId);
            if (cartItemList.size() >0){
                cartItem = cartItemList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItem;


    }

    @Override
    public void delCartItem(CartItem cartItem) {
        String sql = "delete from t_cart_item where id = ?";
        try {
            BaseDao.executeUpdata(sql,cartItem.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

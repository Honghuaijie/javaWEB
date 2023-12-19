package com.hhj.book.service.impl;

import com.hhj.book.dao.CartDao;
import com.hhj.book.pojo.Book;
import com.hhj.book.pojo.CartItem;
import com.hhj.book.pojo.User;
import com.hhj.book.service.BookService;
import com.hhj.book.service.CartService;

import java.text.DecimalFormat;
import java.util.List;

/**
 * ClassName: CartServiceImpl
 * Package: com.hhj.book.service.impl
 * Description:
 *    购物车 业务层实现类
 *
 * @Author honghuaijie
 * @Create 2023/11/10 9:05
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class CartServiceImpl implements CartService {
    private CartDao cartDao = null;
    private BookService bookService = null;

    /**
     * 判断该图书是否在该用户的购物车中
     * @param book  图书
     * @param user 用户
     * @return 如果存在就返回该订单项 不存在就返回null
     */
    public CartItem isInCarts(Book book,User user){
        return  cartDao.isInCart(book.getId(), user.getId());
    }
    /**
     * 获取指定用户的购物车中一共有多少种商品
     * @param user
     * @return
     */
    @Override
    public int getCartCount(User user) {
        return  cartDao.getCartCounts(user.getId());
    }

    /**
     * 添加购物车
     * @param cartItem
     */
    @Override
    public void addCartCount(CartItem cartItem) {
        cartDao.addCart(cartItem);
    }

    /**
     * 获取指定用户的购物项列表（需要注意的是，这个方法内部需要将book的详细信息设置到购物项中去
     * @param user
     * @return
     */
    @Override
    public List<CartItem> getCartList(User user) {
        //不仅要获取所有的购物车项，还有图书的具体信息传入购物车项
        List<CartItem> cartList = cartDao.getCartList(user.getId());
        for (CartItem cartItem : cartList){
            Book book = bookService.getBookById(cartItem.getBook());
            cartItem.setBook(book);
            //可以根据cartItem中的getAllprice来获取总金额
        }
        return cartList;
    }

    /**
     * 修改购物车的购买数量
     * @param cartItem
     * @param buyCount
     */
    @Override
    public void updateCartBuyCounyById(CartItem cartItem, Integer buyCount) {
        cartDao.updateCartBuyCountById(cartItem.getId(),buyCount);
    }

    /**
     * 获取购物车中的总价格
     * @param cartList
     * @return
     */
    @Override
    public double getTotalPrice(List<CartItem> cartList) {
        double totalPrice = 0;
        for (CartItem cartItem : cartList){
            totalPrice += cartItem.getAllPrice();
        }
        return totalPrice;
    }
    @Override
    public void deleteCartItem(CartItem cartItem){
        cartDao.delCartItem(cartItem);
    }

}

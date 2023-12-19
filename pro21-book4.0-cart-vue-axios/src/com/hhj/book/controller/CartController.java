package com.hhj.book.controller;

import com.google.gson.Gson;
import com.hhj.book.pojo.Book;
import com.hhj.book.pojo.CartItem;
import com.hhj.book.pojo.User;
import com.hhj.book.service.CartService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: CartController
 * Package: com.hhj.book.controller
 * Description:
 *  购物车 控制层
 * @Author honghuaijie
 * @Create 2023/11/10 8:55
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class CartController {
    private CartService cartService = null;
    //添加商品到购物车
    public String addCart(Integer bookId, HttpSession session){
        //获取当前用户
        User currUser = (User) session.getAttribute("currUser");
        //判断当前商品是否在当前用户的购物车中 在的话就数量+1，不在的话就新增
        CartItem cartItem= cartService.isInCarts(new Book(bookId),currUser);
        if (cartItem !=null){
            cartService.updateCartBuyCounyById(new CartItem(cartItem.getId()),cartItem.getBuyCount()+1);
        }else{
            cartService.addCartCount(new CartItem(0,new Book(bookId),1,currUser));
        }
        return "redirect:cart.do";
    }

    //获取当前用户的购物车数量
    public String index(HttpSession session){
        //获取当前用户
        User currUser = (User) session.getAttribute("currUser");
        //获取购物车项的总数量
        int cartCount = cartService.getCartCount(currUser);
        session.setAttribute("cartCount",cartCount);
        return "index";
    }

    //获取所有的购物车项，将所有的购物车项放入到session中
    public String getCartList(HttpSession session){
        //获取当前用户
        User currUser = (User) session.getAttribute("currUser");
        List<CartItem> cartList = cartService.getCartList(currUser);
        //计算出所有的金额
        Double totalPrice = cartService.getTotalPrice(cartList);
        session.setAttribute("totalPrice",totalPrice);
        session.setAttribute("cartList",cartList);
        return "cart/cart";
    }

    //修改购物车项的购买数量
    public String upDateBuyCount(Integer buyCount,Integer cartId){
        cartService.updateCartBuyCounyById(new CartItem(cartId),buyCount);
        return null;
    }

    public String cartInfo(HttpSession session){
        //获取当前用户
        User currUser = (User) session.getAttribute("currUser");
        //获取购物车列表
        List<CartItem> cartList = cartService.getCartList(currUser);
        //计算出所有的金额
        Double totalPrice = cartService.getTotalPrice(cartList);
        //将总金额存放到第一个购物项中
        cartList.get(0).setTotalPrice(totalPrice);
        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cartList);
        return "json:" +cartJsonStr;
    }
}

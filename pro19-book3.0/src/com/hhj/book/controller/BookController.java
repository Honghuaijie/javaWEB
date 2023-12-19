package com.hhj.book.controller;

import com.hhj.book.pojo.Book;
import com.hhj.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: BookController
 * Package: com.hhj.book.controller
 * Description:
 *  图书控制层
 * @Author honghuaijie
 * @Create 2023/11/9 19:32
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class BookController {
    private BookService bookService = null;

    //用来显示图书
    public String index(HttpSession session){
        //如果是一上来就访问的该请求，那么user就是空的
        List<Book> books = bookService.getBooks();
        session.setAttribute("books",books);
        //如果user为空，就直接跳转到index页面就行了，没必要再去查找购物车
        if (session.getAttribute("currUser") == null){
            session.setAttribute("currUser",null);
            return "index";
        }

        //跳转给购物车controller，用来保存购物车信息
        return "redirect:cart.do";
    }


}

package com.hhj.book.service;

import com.hhj.book.pojo.Book;

import java.util.List;

/**
 * ClassName: BookService
 * Package: com.hhj.book.service
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/9 19:14
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface BookService {
    //获取所有的图书信息
    List<Book> getBooks();

    //获取指定图书通过ID
    Book getBookById(Book book);
}

package com.hhj.book.service.impl;

import com.hhj.book.dao.BookDao;
import com.hhj.book.pojo.Book;
import com.hhj.book.service.BookService;

import java.util.List;

/**
 * ClassName: BookServiceImpl
 * Package: com.hhj.book.service.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/9 19:14
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class BookServiceImpl implements BookService {
    BookDao bookDao = null;
    @Override
    public List<Book> getBooks() {
        return bookDao.getBookList();

    }

    @Override
    public Book getBookById(Book book) {
        return bookDao.getBookById(book.getId());
    }
}

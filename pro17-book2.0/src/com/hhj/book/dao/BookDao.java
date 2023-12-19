package com.hhj.book.dao;

import com.hhj.book.pojo.Book;

import java.util.List;

/**
 * ClassName: BookDao
 * Package: com.hhj.book.dao
 * Description:
 *  图书类  DAO 接口层
 * @Author honghuaijie
 * @Create 2023/11/9 19:03
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface BookDao {

    List<Book> getBookList();

    //获取指定页上的图书
    List<Book> getBookListByPage(Integer page);

    int getBooksCount();

    //根据ID获取图书信息
    Book getBookById(int id);

}

package com.hhj.book.dao.impl;

import com.hhj.book.dao.BookDao;
import com.hhj.book.pojo.Book;
import com.hhj.myssm.basedao.BaseDao;
import com.hhj.myssm.basedao.JdbcUtilsV2;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * ClassName: BookDaoService
 * Package: com.hhj.book.dao.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/9 19:04
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class BookDaoImpl implements BookDao {
    //获取所有的图书列表
    @Override
    public List<Book> getBookList() {
        String sql = "select * from t_book where bookStatus=0 ;";
        List<Book> books = null;
        try {
             books = BaseDao.executeQuery(Book.class, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    //获取指定页上的图书信息
    @Override
    public List<Book> getBookListByPage(Integer page) {
        String sql = "select * from t_book where bookStatus=0 limit ?,10 ";
        List<Book> books = null;
        try {
            books = BaseDao.executeQuery(Book.class, sql, (page - 1) * 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }


    //获取图书的个数
    @Override
    public int getBooksCount() {
        String sql = "select count(*) from where t_book bookStatus=? ";
        int count = 0;
        try {
            count= BaseDao.queryCount(sql,0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Book getBookById(int id) {
        String sql = "select * from t_book where id = ?";
        Book book = null;
        try {
            List<Book> books = BaseDao.executeQuery(Book.class, sql, id);
            if (books.size() > 0){
                book = books.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }


}

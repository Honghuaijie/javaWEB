package com.hhj1.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JdbcUtilsV2
 * Package: com.hhj.utils
 * Description:
 *  封装了连接数据库的操作  有创建连接和回收连接的操作
 * @Author honghuaijie
 * @Create 2023/10/26 17:06
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class JdbcUtilsV2 {
    private static DataSource dataSource = null; //连接池对象
    //声明线程本地变量
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        //初始化连接池对象
        //获取外部配置文件的输入流
        InputStream ips = JdbcUtilsV2.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //创建连接池
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //对外提供连接
    public static Connection getConnection() throws SQLException {
        //先查看线程本地变量中是否存在
        Connection connection = tl.get();
        //第一次没有
        if (connection == null){
            //线程本地变量没有，连接池获取
            connection = dataSource.getConnection();
            tl.set(connection);  //将连接放入线程本地变量
        }
        return connection;
    }

    public static void freeConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != null){
            tl.remove();//清空线程本地变量数据
            connection.setAutoCommit(true); //回归事务状态
            connection.close(); //回收连接池
        }
    }
}

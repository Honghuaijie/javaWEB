package com.hhj.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JdbcUtils
 * Package: com.hhj.api.utils
 * Description:
 *  v1.0版本工具类
 *      内部包含一个连接池对象，并且对外提供获取连接和回收连接的方法！
 *
 *  小建议：
 *      工具类的方法，推荐写成静态的，外部调用会更加方便！
 *
 *  实现：
 *      属性 连接池对象[实例化一次]
 *          单例模式
 *          static{全局调用一次}
 *      方法
 *          对外提供当前连接的方法
 *          回收当前线程的连接
 *      TODO:
 *          利用线程本地变量，存储连接信息！确保一个线程的多个方法可以获取同一个connection!
 *          优势：事务操作的时候， service 和 dao方法 属于同一个线程，不同传递同一个参数！
 *          大家都可以调用getConnection方法，自动获取的是相同 的连接
 * @Author honghuaijie
 * @Create 2023/10/17 19:29
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

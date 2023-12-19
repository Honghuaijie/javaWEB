package com.hhj.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseDao
 * Package: com.hhj.utils
 * Description:
 *   封装类DQL 和非DQL SQL语句
 * @Author honghuaijie
 * @Create 2023/10/26 17:05
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class BaseDao {
    /**
     * 封装简化非DQL语句
     * @param sql 带占位符的SQL语句
     * @param params  占位符的值 注意，传入占位符的值，必须等于SQL语句的？位置！
     * @return 执行影响的行数
     */
    public static int executeUpdata(String sql,Object... params) throws SQLException {
        //获取连接
        Connection connection = JdbcUtilsV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //5.传入动态值
        //可变参数 可以当做数组来使用
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i,params[i-1]);
        }
        //6.发送SQL语句，并返回结果
        int row = preparedStatement.executeUpdate();

        preparedStatement.close();
        //是否回收连接需要考虑是不是事务
        if (connection.getAutoCommit() == true) {
            //没有开始事务 正常回收连接
            JdbcUtilsV2.freeConnection();
        }
        return row;

    }


    /**
     *
     *  将查询结果封装到实体类集合！
     * @param clazz  要接值的实体类集合的模板对象
     * @param sql  查询语句，要求列名和别名要等于实体类的属性名！
     * @param params  占位符的值 要和？位置对象对应
     * @return 查询的实体列的集合
     * @param <T> 声明的结果的泛型
     * @throws SQLException
     */
    public static  <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //获取连接
        Connection connection = JdbcUtilsV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if(params != null && params.length != 0){
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i,params[i-1]);
            }
        }


        ResultSet resultSet = preparedStatement.executeQuery();

        //结果集解析
        List<T> list = new ArrayList<>();
        //获取列信息
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount(); //获取列的总数
        while (resultSet.next()){
            //一行数据，对应一个T类型的对象
            T t = clazz.newInstance();  //调用类的无参构造器来实例化对象

            //自动遍历列，注意是从1开始
            for (int i = 1; i <= columnCount; i++) {
                Object o = resultSet.getObject(i);//列的属性值
                String propertyName = metaData.getColumnLabel(i).toLowerCase(); //列的属性名
                //使用反射，给对象的属性赋值
                //根据列名取到具体的属性，然后通过属性给对象赋值
                Field field = clazz.getDeclaredField(propertyName); //获取该类的属性
                field.setAccessible(true); //打破private 权限
                /**
                 * 参数1：要赋值的具体的对象, 如果属性是静态，第一个参数可以为null
                 * 参数2：具体的属性值
                 */
                field.set(t,o);
            }
            list.add(t);
        }
        //判断该事务有没有开启，如果没有开启就回收连接
        if (connection.getAutoCommit()) {
            JdbcUtilsV2.freeConnection();
        }
        preparedStatement.close();
        resultSet.close();
        return list;

    }
}

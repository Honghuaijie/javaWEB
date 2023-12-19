package com.hhj.myssm.basedao;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public static  <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, ParseException {
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
        //设置时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取列信息
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount(); //获取列的总数
        while (resultSet.next()){
            //一行数据，对应一个T类型的对象
            T t = clazz.newInstance();  //调用类的无参构造器来实例化对象

            //自动遍历列，注意是从1开始
            for (int i = 1; i <= columnCount; i++) {

                Object o = resultSet.getObject(i);//列的属性值
                String propertyName = metaData.getColumnLabel(i); //列的属性名
                //使用反射，给对象的属性赋值
                //根据列名取到具体的属性，然后通过属性给对象赋值
                Field field = clazz.getDeclaredField(propertyName); //获取该类的属性
                //先判断
                if (field !=null){
                    //获取当前属性的类型名称
                    String typeName = field.getType().getName();
                    //判断该类型是否是自定义类型
                    if (isMyType(typeName)){
                        Class typeNameClass = Class.forName(typeName);
                        Constructor constructor = typeNameClass.getDeclaredConstructor(Integer.class);
                        o = constructor.newInstance(o);
                    }else if ("java.util.Date".equals(typeName)){ //如果当前类型是时间类型
                        Timestamp timestamp = resultSet.getTimestamp(i);
                        // 3.使用SimpleDateFormat，将其格式化为字符串
                        String format1 = format.format(timestamp);
                        // 4.再将该字符串解析为java.util.Date类型，最后赋值给columnValue
                        o = format.parse(format1);
                    }
                }
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

    //用来判断属性是否是自定义类
    private static boolean isNotMyType(String typeName){
        return "java.lang.Integer".equals(typeName)
                || "java.lang.String".equals(typeName)
                || "java.util.Date".equals(typeName)
                || "java.sql.Date".equals(typeName)
                || "java.lang.Double".equals(typeName);
    }

    private static boolean isMyType(String typeName){
        return !isNotMyType(typeName);
    }
}

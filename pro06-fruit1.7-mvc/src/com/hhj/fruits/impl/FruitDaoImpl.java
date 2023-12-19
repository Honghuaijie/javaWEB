package com.hhj.fruits.impl;

import com.hhj.fruits.Fruit;
import com.hhj.fruits.dao.FruitDao;
import com.hhj.utils.BaseDao;
import com.hhj.utils.JdbcUtilsV2;

import java.sql.*;
import java.util.List;

/**
 * ClassName: FruitDaoImpl
 * Package: com.hhj.fruits.impl
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/26 17:12
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class FruitDaoImpl implements FruitDao {
    @Override
    public List<Fruit> getFruitList(String keyword,Integer page) {
        String sql = "select * from t_fruit where fname like ? or remark like ? limit ?,5;";
        List<Fruit> fruits = null;
        try {
             fruits = BaseDao.executeQuery(Fruit.class, sql,"%" + keyword + "%","%" + keyword + "%",((page-1) * 5));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return fruits;

    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        String sql = "select * from t_fruit where fid = ?;";
        List<Fruit> fruits = null;
        try {
            fruits = BaseDao.executeQuery(Fruit.class, sql, fid);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return fruits.get(0);
    }

    @Override
    public void updateFruit(Fruit fruit)  {
        String sql = "update t_fruit set fname=?,price=?,fcount=?,remark=? where fid=?";
        try {
             BaseDao.executeUpdata(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delFruit(int fid) {
        String sql = "delete from t_fruit where fid = ?";
        try {
            BaseDao.executeUpdata(sql,fid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        try {
            BaseDao.executeUpdata(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getFruitCount(String keyword)  {
        String sql = "select count(*) as counts from t_fruit where fname like ? or remark like ?";
        Connection connection = null;  //获取连接
        PreparedStatement preparedStatement = null;  //用来发送预编译
        ResultSet resultSet = null;  //结果集
        Integer counts = 0;
        try {
            connection = JdbcUtilsV2.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //占位符赋值
            preparedStatement.setString(1,"%" + keyword + "%");
            preparedStatement.setString(2,"%" + keyword + "%");
            resultSet = preparedStatement.executeQuery();
            //由于resultset默认指向的是-1.所以需要往下移动一下
            resultSet.next();  //移动到第一行
            counts =  resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtilsV2.freeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return counts;
    }
}

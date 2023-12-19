package com.hhj1.fruits.impl;

import com.hhj1.fruits.Fruit;
import com.hhj1.fruits.dao.FruitDao;
import com.hhj1.utils.BaseDao;
import com.hhj1.utils.JdbcUtilsV2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "select * from t_fruit where fname like ? or remark like ? limit ?,5";
        List<Fruit> fruitList = null;
        try {
            fruitList = BaseDao.executeQuery(Fruit.class,sql,("%" + keyword + "%"),("%" + keyword + "%"),(page-1)*5);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return fruitList;
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        String sql = "select * from t_fruit where fid = ?";
        Fruit fruit = null;
        try {
            fruit = BaseDao.executeQuery(Fruit.class,sql,fid).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return fruit;

    }

    @Override
    public void updateFruit(Fruit fruit)  {
        String sql = "update  t_fruit set fname=?, price=?,fcount=?,remark=? where fid = ?";
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
        String sql = "insert into t_fruit values(?,?,?,?,?)";
        try {
            BaseDao.executeUpdata(sql,0,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //根据关键词获取全部的水果记录
    @Override
    public int getFruitCount(String keyword)  {
        String sql = "select count(*) from t_fruit where fname like ? or remark like ?;";
        int counts = 0;
        try {
            Connection connection = JdbcUtilsV2.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%" + keyword + "%");
            preparedStatement.setString(2,"%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            counts = resultSet.getInt(1);

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

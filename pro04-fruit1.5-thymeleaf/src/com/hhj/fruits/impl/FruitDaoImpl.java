package com.hhj.fruits.impl;

import com.hhj.fruits.Fruit;
import com.hhj.fruits.dao.FruitDao;
import com.hhj.utils.BaseDao;

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
    public List<Fruit> getFruitList() {
        String sql = "select * from t_fruit; ";
        List<Fruit> fruits = null;
        try {
             fruits = BaseDao.executeQuery(Fruit.class, sql);
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
        int i = 0;
        try {
             i = BaseDao.executeUpdata(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (i > 0){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
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
}

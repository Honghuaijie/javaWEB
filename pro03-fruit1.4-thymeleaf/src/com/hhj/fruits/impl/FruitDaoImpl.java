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
        String sql = "select * from t_fruit;";
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
        return null;
    }


}

package com.hhj.fruits.service.imp;

import com.hhj.fruits.Fruit;
import com.hhj.fruits.exception.FruitServiceException;
import com.hhj.fruits.service.FruitService;
import com.hhj.fruits.dao.FruitDao;
import com.hhj.utils.JdbcUtilsV2;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: FruitServletImp
 * Package: com.hhj.fruits.biz.imp
 * Description:
 *     业务层 实现类
 * @Author honghuaijie
 * @Create 2023/11/3 14:38
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class FruitServiceImp implements FruitService {
    //虽然这里赋值为null但是在ClassPathXMLApplicationContext中我们通过反射已经将依赖设置好了
    private FruitDao fruitDao = null;
    @Override
    public List<Fruit> getFruitList(String keyword, Integer page) {
        try {
            System.out.println("getFruitList -> " + JdbcUtilsV2.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitServiceException("FruitService 出错了");
        }
        return fruitDao.getFruitList(keyword,page);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        try {
            return fruitDao.getFruitByFid(fid);
        }catch (Exception e){
            throw new FruitServiceException("FruitService 出错了");
        }

    }

    @Override
    public void updateFruit(Fruit fruit) {
        try {
            fruitDao.updateFruit(fruit);
        }catch (Exception e){
            throw new FruitServiceException("FruitService 出错了");
        }

    }

    @Override
    public void delFruit(int fid) {
        try {
            fruitDao.delFruit(fid);
        }catch (Exception e){
            throw new FruitServiceException("FruitService 出错了");
        }
    }

    @Override
    public void insertFruit(Fruit fruit) {
        fruitDao.insertFruit(fruit);


    }

    //获取页数
    @Override
    public int getFruitCount(String keyword) {
        try {
            System.out.println("getFruitCount -> " + JdbcUtilsV2.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitServiceException("FruitService 出错了");

        }
        //获取总记录数
        int fruitCount = fruitDao.getFruitCount(keyword);
        return (fruitCount+4)/5;
    }
}

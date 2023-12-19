package com.hhj1.fruits.dao;

import com.hhj1.fruits.Fruit;

import java.util.List;

/**
 * ClassName: FruitDap
 * Package: com.hhj.fruits.dao
 * Description:
 *   获取所有的库存列表
 * @Author honghuaijie
 * @Create 2023/10/26 17:11
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface FruitDao {
    //获取指定页码上的库存列表信息，每页显示五条,并且根据关键字模糊查询
    List<Fruit> getFruitList(String keyword,Integer page);

    //获取fid获取特定的水果库存信息
    Fruit getFruitByFid(Integer fid);

    //修改指定的fruit信息
    void updateFruit(Fruit fruit);

    //删除指定ID的水果信息
    void delFruit(int fid);

    //添加信息到数据库
    void insertFruit(Fruit fruit);

    //获取库存总记录条数(模糊查询)
    int getFruitCount(String keyword);
}

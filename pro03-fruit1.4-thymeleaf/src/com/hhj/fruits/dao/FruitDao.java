package com.hhj.fruits.dao;

import com.hhj.fruits.Fruit;

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
    //获取所有库存列表信息
    List<Fruit> getFruitList();

    //获取fid获取特定的水果库存信息
    Fruit getFruitByFid(Integer fid);
}

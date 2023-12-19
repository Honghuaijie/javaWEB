package com.hhj.fruit.dao;

import com.hhj.fruit.pojo.Fruit;

import java.util.List;

/**
 * ClassName: FruitDAO
 * Package: com.hhj.fruit.dao
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/23 9:33
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();

    //新增库存
    boolean addFruit(Fruit fruit);

    //修改库存
    boolean updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //删除特定库存记录
    boolean delFruit(String fname);
}

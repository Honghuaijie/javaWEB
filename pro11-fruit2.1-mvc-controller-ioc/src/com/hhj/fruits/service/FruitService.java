package com.hhj.fruits.service;

import com.hhj.fruits.Fruit;

import java.util.List;

/**
 * ClassName: FruitServlet
 * Package: com.hhj.fruits.biz
 * Description:
 *      业务层接口
 * @Author honghuaijie
 * @Create 2023/11/3 14:36
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */

public interface FruitService {
    //获取指定页上的记录
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

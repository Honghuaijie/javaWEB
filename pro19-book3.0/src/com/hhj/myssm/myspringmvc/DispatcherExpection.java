package com.hhj.myssm.myspringmvc;

/**
 * ClassName: DispatcherExpection
 * Package: com.hhj.fruits.exception
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/4 18:40
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class DispatcherExpection extends RuntimeException{
    public DispatcherExpection(String msg){
        super(msg);
    }
}

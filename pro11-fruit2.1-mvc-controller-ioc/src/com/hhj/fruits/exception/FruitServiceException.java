package com.hhj.fruits.exception;

/**
 * ClassName: FruitDAOException
 * Package: com.hhj.fruits.exception
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/4 17:34
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class FruitServiceException extends RuntimeException{
    public FruitServiceException(String msg){
        super(msg);
    }
}

package com.hhj.myssm.ioc;

/**
 * ClassName: BeanFactory
 * Package: com.hhj.io
 * Description:
 *      Bean工厂，用来存储所有的bean
 * @Author honghuaijie
 * @Create 2023/11/3 16:21
 * @Version 1.0

 */
public interface BeanFactory {
    Object getBean(String id);
}

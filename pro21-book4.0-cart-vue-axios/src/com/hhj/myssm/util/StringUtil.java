package com.hhj.myssm.util;

/**
 * ClassName: StringUtil
 * Package: com.hhj.myssm.util
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/10/27 17:08
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class StringUtil {
    //判断某个字符串是否为空或为null
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpt(String str){
        return !isEmpty(str);
    }
}

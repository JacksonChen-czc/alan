package cn.chenzecheng.alan.common.exception;

import cn.chenzecheng.alan.common.enums.MyErrorEnum;

/**
 * @author JacksonChen
 * @date 2022/7/18 22:08
 */
public class MyAssertUtil {


    public static void notNull(Object object, MyErrorEnum errorEnum) {
        if (null == object) {
            throw new BizException(errorEnum);
        }
    }
}

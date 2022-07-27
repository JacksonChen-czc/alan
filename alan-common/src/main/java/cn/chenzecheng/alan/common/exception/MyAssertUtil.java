package cn.chenzecheng.alan.common.exception;

import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.common.enums.MyErrorEnum;
import cn.chenzecheng.alan.common.enums.ResultCodeEnum;

import java.util.Objects;

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

    public static void isResultSuc(MyResult<?> result) {
        if (!Objects.equals(result.getCode(), ResultCodeEnum.SUCCESS.getCode())) {
            throw new BizException(MyErrorEnum.REMOTE_ERROR);
        }
    }

    public static void isTrue(boolean bol) {
        if (!Boolean.TRUE.equals(bol)) {
            throw new BizException(MyErrorEnum.SYS_ERR);
        }
    }
}

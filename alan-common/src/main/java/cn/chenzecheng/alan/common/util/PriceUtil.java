package cn.chenzecheng.alan.common.util;

import java.math.BigDecimal;

/**
 * @author JacksonChen
 * @date 2022/7/27 22:49
 */
public class PriceUtil {

    private static final BigDecimal MULTIPLICAND = new BigDecimal(100);

    /**
     * 将BigDecimal格式的以元为单位的价格转换为Long格式以分为单位的价格
     * todo 增加判断有没有超过Long的最大值
     *
     * @param price
     * @return
     */
    public static Long convertToLong(BigDecimal price) {
        return price.multiply(MULTIPLICAND).longValue();
    }
}

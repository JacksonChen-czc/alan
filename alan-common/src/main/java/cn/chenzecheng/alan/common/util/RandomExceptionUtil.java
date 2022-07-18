package cn.chenzecheng.alan.common.util;

import cn.chenzecheng.alan.common.constants.CommonConstants;
import cn.chenzecheng.alan.common.exception.BizException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 随机异常工具类
 * 模仿大型系统不稳定，偶尔出现系统异常和网络延迟
 *
 * @author JacksonChen
 * @date 2022/7/16 17:33
 */
@Slf4j
public class RandomExceptionUtil {

    private static Random random = new Random();

    public static void randomExAndSleep() {
        randomException();
        randomSleep();
    }

    private static void randomException() {
        if (random.nextInt(CommonConstants.ONE_HUNDRED) < CommonConstants.RANDOM_EXCEPTION_RATIO) {
            log.warn("发生了随机异常");
            throw new BizException("发生随机异常。");
        }
    }

    @SneakyThrows
    private static void randomSleep() {
        if (random.nextInt(CommonConstants.ONE_HUNDRED) < CommonConstants.RANDOM_SLEEP_RATIO) {
            log.warn("发生了随机延迟");
            Thread.sleep(CommonConstants.RANDOM_SLEEP_TIME);
        }
    }
}

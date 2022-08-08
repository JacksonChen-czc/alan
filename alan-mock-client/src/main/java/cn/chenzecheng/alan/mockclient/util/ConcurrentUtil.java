package cn.chenzecheng.alan.mockclient.util;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiConsumer;

/**
 * 并发执行工具
 *
 * @author JacksonChen
 * @date 2022/7/28 15:28
 */
@Slf4j
public class ConcurrentUtil {

    /**
     * 提交一定数量的任务到线程池中，并等待所有线程提交完成之后，同时执行consumer方法
     *
     * @param paramTs         参数T集合，任务数=集合元素个数，consumer的第一个参数，每次执行取集合中一个元素
     * @param paramD          参数D，consumer的第二个参数，每次执行consumer都取该参数
     * @param consumer        并发执行的方法
     * @param executorService 线程池
     * @param <T>             consumer的第一个参数
     * @param <D>             consumer的第二个参数
     */
    public static <T, D> void concurrentConsume(List<T> paramTs, D paramD, BiConsumer<T, D> consumer, ExecutorService executorService) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        List<Future<Integer>> futures = Lists.newArrayList();
        readWriteLock.writeLock().lock();
        try {
            for (T t : paramTs) {
                // 使用线程池或实现并发
                Future<Integer> submit = executorService.submit(() -> {
                    log.debug("线程启动，等待全部线程提交完毕完成");
                    readWriteLock.readLock().lock();
                    try {
                        log.debug("全部线程提交完成，线程继续运行");
                        consumer.accept(t, paramD);
                    } finally {
                        readWriteLock.readLock().unlock();
                    }
                    return 1;
                });
                futures.add(submit);
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
        // 等待子线程全部结束后
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.warn("future.get异常", e);
//                throw new RuntimeException(e);
            }
        });
    }
}

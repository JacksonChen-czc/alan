package cn.chenzecheng.alan.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 数据库数据初始化
 *
 * @author JacksonChen
 * @date 2022/7/19 22:03
 */
@SpringBootTest
public class OrderDbInit {


    private ExecutorService threadPool = new ThreadPoolExecutor(16, 32, 10L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void insert() {
    }

    @Test
    public void insertBatch() {
        // 生成100w的商品，16线程大概需要20分钟，可以根据性能自助调整
        /* 数据库连接增加 rewriteBatchedStatements=true 参数，可以大量提升批量插入的效率，*/
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                long start1 = System.currentTimeMillis();
                int size = 1000;

                long end1 = System.currentTimeMillis();
                System.out.println("第【" + finalI + "】批商品插入完成，数量：" + size + "，耗时：" + (end1 - start1));
            });
        }
        threadPool.shutdown();
        while (!threadPool.isTerminated()) {
            System.out.println("任务未完成，继续等待...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("线程池执行任务结束，耗时：" + (end - start));
    }
}

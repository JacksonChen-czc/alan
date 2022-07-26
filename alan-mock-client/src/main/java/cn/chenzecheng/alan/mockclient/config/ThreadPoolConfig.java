package cn.chenzecheng.alan.mockclient.config;

import cn.chenzecheng.alan.mockclient.constants.PoolConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 *
 * @author JacksonChen
 * @date 2022/7/26 11:05
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "secKillThreadPool")
    public ExecutorService threadPool() {
        // 队列长度为0，无缓冲线程池
        return new ThreadPoolExecutor(PoolConstants.MAX_CONCURRENT_THREAD, PoolConstants.MAX_CONCURRENT_THREAD, 30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024), new NamedThreadFactory("secKill-pool"), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}

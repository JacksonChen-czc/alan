package cn.chenzecheng.alan.goods.redis;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRedissonApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {
        redissonClient.getBucket("hello").set("bug");
        String test = (String) redissonClient.getBucket("hello").get();
        System.out.println(test);

    }

}
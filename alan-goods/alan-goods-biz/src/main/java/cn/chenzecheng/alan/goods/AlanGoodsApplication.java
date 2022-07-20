package cn.chenzecheng.alan.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author JacksonChen
 * @date 2022/7/15 21:09
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.chenzecheng.alan")
public class AlanGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlanGoodsApplication.class, args);
    }
}

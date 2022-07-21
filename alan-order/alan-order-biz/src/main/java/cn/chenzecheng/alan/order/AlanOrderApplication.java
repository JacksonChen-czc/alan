package cn.chenzecheng.alan.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author JacksonChen
 * @date 2022/7/15 21:09
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.chenzecheng.alan")
@EnableFeignClients(basePackages = "cn.chenzecheng.alan")
public class AlanOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlanOrderApplication.class, args);
    }
}

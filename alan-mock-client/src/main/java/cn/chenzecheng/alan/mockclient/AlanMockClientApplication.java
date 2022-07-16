package cn.chenzecheng.alan.mockclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author JacksonChen
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.chenzecheng.alan")
@EnableFeignClients(basePackages = "cn.chenzecheng.alan")
public class AlanMockClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlanMockClientApplication.class, args);
    }

}

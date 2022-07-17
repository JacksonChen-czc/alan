package cn.chenzecheng.alan.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author JacksonChen
 * @date 2022/7/15 21:09
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("cn.chenzecheng.alan.account.mapper")
public class AlanAccountApplication {


    public static void main(String[] args) {
        SpringApplication.run(AlanAccountApplication.class, args);
    }
}
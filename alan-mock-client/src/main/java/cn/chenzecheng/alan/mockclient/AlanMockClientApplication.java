package cn.chenzecheng.alan.mockclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlanMockClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlanMockClientApplication.class, args);
    }

}

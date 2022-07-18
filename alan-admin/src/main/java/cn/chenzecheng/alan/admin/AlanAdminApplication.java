package cn.chenzecheng.alan.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author JacksonChen
 */
//@EnableDiscoveryClient
@SpringBootApplication
@EnableAdminServer
public class AlanAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlanAdminApplication.class, args);
    }

}

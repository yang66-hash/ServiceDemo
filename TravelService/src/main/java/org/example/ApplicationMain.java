package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yang
 * @version 1.0
 * @date 2022/12/01
 */
@SpringBootApplication
//@MClient
@com.wangteng.mclient.annotation.MClient
@EnableEurekaClient
public class ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
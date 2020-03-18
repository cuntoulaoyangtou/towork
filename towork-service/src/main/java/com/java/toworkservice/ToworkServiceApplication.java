package com.java.toworkservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@MapperScan("com.java.toworkservice.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class ToworkServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToworkServiceApplication.class, args);
    }

}

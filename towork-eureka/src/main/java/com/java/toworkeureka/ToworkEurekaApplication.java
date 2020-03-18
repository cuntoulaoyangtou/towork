package com.java.toworkeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ToworkEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToworkEurekaApplication.class, args);
    }

}

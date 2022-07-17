package com.creditpipeline.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApplicationSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpringBoot.class, args);
    }

}

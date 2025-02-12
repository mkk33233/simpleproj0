package com.example.agriculturesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example.agriculturesystem")

@EnableScheduling  // 启用定时任务
public class AgricultureSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgricultureSystemApplication.class, args);
    }

}

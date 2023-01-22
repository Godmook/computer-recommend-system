package com.godmook.computerrecommendsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ComputerRecommendSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerRecommendSystemApplication.class, args);
    }

}

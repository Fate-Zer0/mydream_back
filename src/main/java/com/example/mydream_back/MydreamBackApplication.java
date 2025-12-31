package com.example.mydream_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.example.mydream_back.dao")
@EnableAsync
@SpringBootApplication
public class MydreamBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MydreamBackApplication.class, args);
    }

}

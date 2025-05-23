package com.example.mydream_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mydream_back.dao")
@SpringBootApplication
public class MydreamBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MydreamBackApplication.class, args);
    }

}

package com.avir.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.avir.ai.mapper")
public class AvicAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvicAiApplication.class, args);
    }

}

package com.knockknock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.knockknock.mapper")
@SpringBootApplication
public class JavaCampusAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaCampusAssistantApplication.class, args);
    }

}

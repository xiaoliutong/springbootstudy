package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:sql.xml"})
@MapperScan(basePackages = {"com.springboot.mapper"})
public class StartMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartMybatisApplication.class, args);
    }
}

package com.example.oneapibackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@MapperScan("com.example.oneapibackend.mapper")
public class OneApiBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(OneApiBackendApplication.class, args);
        System.out.println("启动成功");
    }

}

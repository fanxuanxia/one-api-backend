package com.example.oneapibackend.service.impl;

import com.example.oneapibackend.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserInfoServiceImplTest {
    @Resource
    UserInfoService userInfoService;

    @Test
    public void registerTest(){
        Integer integer = userInfoService.UserRegister("12345677990", "dbhvfgvdfjksdj");
        System.out.println(integer);

    }


}
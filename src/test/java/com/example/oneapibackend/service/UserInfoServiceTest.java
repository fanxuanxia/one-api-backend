package com.example.oneapibackend.service;

import com.example.oneapibackend.model.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserInfoServiceTest {
    @Resource
    private UserInfoService userInfoService;

    @Test
    public void test(){
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("fxx3");
        userInfo.setPassword("11111");
        userInfo.setRole("admin");
        userInfo.setName("星黛露");
        userInfoService.save(userInfo);
        System.out.println(userInfo.getId());

    }


}
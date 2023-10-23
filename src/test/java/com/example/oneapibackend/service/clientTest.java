package com.example.oneapibackend.service;

import com.example.interfaceclientsdk.client.apiClient;
import com.example.interfaceclientsdk.model.ClientUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class clientTest {
    @Resource
    private apiClient client;

    @Test
    void contextLoad2(){
        ClientUser user = new ClientUser();
        user.setName("vvvvvvvvvv");
        String hahahah = client.getUser(user);
        System.out.println(hahahah);
    }
}

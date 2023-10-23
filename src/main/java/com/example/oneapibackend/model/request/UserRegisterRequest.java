package com.example.oneapibackend.model.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String account;
    private String password;
}


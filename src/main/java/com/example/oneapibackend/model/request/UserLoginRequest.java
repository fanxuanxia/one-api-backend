package com.example.oneapibackend.model.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}

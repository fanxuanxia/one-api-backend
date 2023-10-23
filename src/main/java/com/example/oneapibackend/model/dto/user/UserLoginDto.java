package com.example.oneapibackend.model.dto.user;

import lombok.Data;

@Data
public class UserLoginDto {
    private Integer id;
    private String username;
    private String account;
    private String role;
    private String portrait;
    private String token;

}

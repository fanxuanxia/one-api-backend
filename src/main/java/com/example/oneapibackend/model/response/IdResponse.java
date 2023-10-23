package com.example.oneapibackend.model.response;

import lombok.Data;

@Data
public class IdResponse {
    public Integer id;

    public IdResponse(Integer id) {
        this.id = id;
    }
}

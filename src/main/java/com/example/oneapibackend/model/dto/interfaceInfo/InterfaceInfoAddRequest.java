package com.example.oneapibackend.model.dto.interfaceInfo;

import lombok.Data;

@Data
public class InterfaceInfoAddRequest{
    /**
     *
     */
    private String name;

    /**
     *
     */
    private String description;
    /**
     *
     */
    private String domain;

    /**
     *
     */
    private String method;

    /**
     *
     */
    private String path;

    /**
     *
     */
    private String headers;

    /**
     *
     */
    private String requestParams;

    /**
     *
     */
    private String responseHeaders;

}




package com.example.oneapibackend.model.dto.interfaceInfo;

import lombok.Data;

@Data
public class InterfaceInfoModifyRequest {

    private Integer id;
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




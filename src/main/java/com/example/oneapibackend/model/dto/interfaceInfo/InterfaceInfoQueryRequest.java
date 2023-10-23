package com.example.oneapibackend.model.dto.interfaceInfo;

import com.example.oneapibackend.common.PageResult;
import lombok.Data;

import java.io.Serializable;

@Data
public class InterfaceInfoQueryRequest extends PageResult implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private String domain;

    private String method;

    private String path;

    private String headers;

    private String requestParams;

    private String responseHeaders;

    private String status;

    private static final long serialVersionUID = 1L;

}

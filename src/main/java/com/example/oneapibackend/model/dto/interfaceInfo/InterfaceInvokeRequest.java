package com.example.oneapibackend.model.dto.interfaceInfo;

import lombok.Data;

@Data
public class InterfaceInvokeRequest {
    /**
     * 接口id
     */
    private Integer interfaceId;

    /**
     * 请求参数
     */
    private String userRequestParams;

    /**
     * 请求Body
     */
    private String userRequestBody;

}

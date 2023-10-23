package com.example.oneapibackend.common;

public enum InterfaceStatus {
    INIT("INIT", "初始化"),
    ONLINE("ON", "上线"),
    OFFLINE("OFF", "下线");

    private final String code;
    private final String description;

    InterfaceStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

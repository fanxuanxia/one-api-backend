package com.example.oneapibackend.common;

public enum ErrorCode {
    SUCCESS(200, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),

    NOT_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    USER_EXITS(40102, "用户已存在"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败");

    private final int code;
    private final String desc;

    ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDescription() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}

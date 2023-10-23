package com.example.oneapibackend.common;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private int code;
    private T result;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private  BaseResponse(int code, T result, String msg){
        this.code  = code;
        this.result = result;
        this.msg = msg;
    }

    public static BaseResponse success(Object result){
        return  new BaseResponse(ErrorCode.SUCCESS.getCode(), result,"");
    }

    public static BaseResponse error(ErrorCode errCode, String errMsg){
        return  new BaseResponse(errCode.getCode(), null,errMsg);
    }

    public static BaseResponse error(ErrorCode errCode){
        return  new BaseResponse(errCode.getCode(), null,errCode.getDescription());
    }
}

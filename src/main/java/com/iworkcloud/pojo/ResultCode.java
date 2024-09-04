package com.iworkcloud.pojo;

public enum ResultCode {

    SUCCESS(200,"成功"),FAILED(400,"失败");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
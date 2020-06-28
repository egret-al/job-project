package com.utils;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/4/20 17:21
 */
public enum ReturnCode {
    /** 请求失败 */
    FAILED(0, "操作失败"),
    /** 请求成功 */
    SUCCESS(1, "操作成功");

    /** 返回状态码 */
    private Integer code;

    /** 返回消息 */
    private String message;

    ReturnCode(Integer code, String message) {
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

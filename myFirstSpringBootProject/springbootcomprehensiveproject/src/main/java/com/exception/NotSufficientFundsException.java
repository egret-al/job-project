package com.exception;

/**
 * 余额不足异常处理类
 * @author 冉堃赤
 * @date 2020/3/29 11:20
 */
public class NotSufficientFundsException extends RuntimeException {

    public NotSufficientFundsException(String message) {
        super(message);
    }
}

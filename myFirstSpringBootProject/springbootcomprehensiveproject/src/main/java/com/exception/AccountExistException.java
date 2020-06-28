package com.exception;

/**
 * @author 冉堃赤
 * @date 2020/3/22 14:50
 */
public class AccountExistException extends RuntimeException {

    public AccountExistException(String message) {
        super(message);
    }
}

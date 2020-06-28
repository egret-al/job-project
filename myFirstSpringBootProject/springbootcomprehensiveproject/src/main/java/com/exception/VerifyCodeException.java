package com.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/4/20 14:58
 */
public class VerifyCodeException extends AuthenticationException {
    public VerifyCodeException(String msg) {
        super(msg);
    }
}

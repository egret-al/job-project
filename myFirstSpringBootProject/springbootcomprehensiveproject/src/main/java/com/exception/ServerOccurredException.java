package com.exception;

/**
 * @author 冉堃赤
 * @date 2020/3/27 19:00
 */
public class ServerOccurredException extends RuntimeException {

    public ServerOccurredException(String message) {
        super(message);
    }
}

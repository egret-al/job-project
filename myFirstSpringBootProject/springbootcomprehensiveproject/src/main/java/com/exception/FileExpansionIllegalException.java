package com.exception;

/**
 * 文件扩展名异常
 * @author 冉堃赤
 * @date 2020/3/28 10:55
 */
public class FileExpansionIllegalException extends RuntimeException {

    public FileExpansionIllegalException(String message) {
        super(message);
    }
}

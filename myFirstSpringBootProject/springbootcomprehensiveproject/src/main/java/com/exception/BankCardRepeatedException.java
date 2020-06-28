package com.exception;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/26 11:04
 */
public class BankCardRepeatedException extends RuntimeException {

    public BankCardRepeatedException() {
        super("银行卡重复！");
    }

    public BankCardRepeatedException(String message) {
        super(message);
    }
}

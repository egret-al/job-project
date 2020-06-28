package com.utils;

import java.util.UUID;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/4/20 17:14
 */
public class UUIDUtils {

    private UUIDUtils() {}

    /**
     * 简单模式，简单模式为不带'-'的UUID字符串
     * @return 生成32位的uuid
     */
    public static String GeneratorUUIDOfSimple() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}

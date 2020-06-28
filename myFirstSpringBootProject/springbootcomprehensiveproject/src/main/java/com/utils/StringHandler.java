package com.utils;

import org.springframework.stereotype.Component;

/**
 * @author 冉堃赤
 * @date 2020/4/2 19:57
 */
@Component("stringHandler")
public class StringHandler {

    /**
     * 解析天气字符串
     * @param str 解析的温度字符串
     * @return 温度数字
     * @throws Exception 格式化失败
     */
    public int tackleWeather(String str) throws Exception {
        try {
            String result = str.substring(str.indexOf(" ") + 1, str.lastIndexOf("℃"));
            return Integer.parseInt(result);
        } catch (Exception e) {
            throw new Exception("字符串转化失败");
        }
    }
}

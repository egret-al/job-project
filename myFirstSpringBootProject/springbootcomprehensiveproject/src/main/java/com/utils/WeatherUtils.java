package com.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 天气查询工具类
 * @author 冉堃赤
 * @date 2020/3/28 17:00
 */
@Component("weatherUtils")
public class WeatherUtils {

    @Autowired
    private RestTemplate restTemplate;

    public String getWeatherCondition(String cityName) {
        String targetUrl = "http://wthrcdn.etouch.cn/weather_mini?city=" + cityName;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(targetUrl, String.class);

        if (200 == responseEntity.getStatusCodeValue()) {
            return responseEntity.getBody();
        } else {
            return "error with code : " + responseEntity.getStatusCodeValue();
        }
    }
}

package com.weather;

import com.SpringbootApplication;
import com.entity.weather.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.WeatherUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/8 23:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestWeather {

    @Autowired
    private WeatherUtils weatherUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAnalysisWeatherString() throws Exception {
        String weatherCondition = weatherUtils.getWeatherCondition("重庆");
        System.out.println(weatherCondition);

        Weather weather = objectMapper.readValue(weatherCondition, Weather.class);
        System.out.println(weather);
    }
}

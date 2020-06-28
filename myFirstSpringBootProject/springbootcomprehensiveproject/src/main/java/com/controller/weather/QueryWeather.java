package com.controller.weather;

import com.entity.weather.Weather;
import com.entity.weather.WeatherSingleDay;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.StringHandler;
import com.utils.WeatherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据传入的城市名称，提供天气服务的controller
 * @author 冉堃赤
 * @date 2020/3/27 20:44
 */
@RestController
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class QueryWeather {

    @Autowired
    private WeatherUtils weatherUtils;

    @Autowired
    private StringHandler stringHandler;

    @Value("${city-name}")
    private String cityName;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/getWeather", method = RequestMethod.POST)
    public String getWeatherCondition(@RequestParam("city") String cityName) {
        return weatherUtils.getWeatherCondition(cityName);
    }

    @RequestMapping(value = "/getWeatherData", method = RequestMethod.POST)
    public Object getWeatherDatas() throws Exception {
        Map<Object, Object> map = new HashMap<>(10);
        List<Integer> lows = new ArrayList<>(10);
        List<Integer> highs = new ArrayList<>(10);
        List<String> names = new ArrayList<>(10);
        String weatherCondition = weatherUtils.getWeatherCondition(cityName);
        if (!weatherCondition.contains("error")) {
            Weather weather = objectMapper.readValue(weatherCondition, Weather.class);
            lows.addAll(convertLowTemperature(weather));
            highs.addAll(convertHighTemperature(weather));

            names.add(weather.getData().getYesterday().getDate());
            for (WeatherSingleDay weatherSingleDay : weather.getData().getForecast()) {
                names.add(weatherSingleDay.getDate());
            }

            map.put("low", lows);
            map.put("high", highs);
            map.put("name", names);
        }
        return map;
    }

    /**
     * 解析天气字符串获取低温
     * @param weather
     * @return
     */
    private List<Integer> convertLowTemperature(Weather weather) throws Exception {
        String low = weather.getData().getYesterday().getLow();
        List<Integer> lows = new ArrayList<>(10);
        lows.add(stringHandler.tackleWeather(low));

        List<WeatherSingleDay> forecast = weather.getData().getForecast();
        for (WeatherSingleDay weatherSingleDay : forecast) {
            lows.add(stringHandler.tackleWeather(weatherSingleDay.getLow()));
        }
        return lows;
    }

    /**
     * 解析天气字符串获取高温
     * @param weather
     * @return
     * @throws Exception
     */
    private List<Integer> convertHighTemperature(Weather weather) throws Exception {
        String high = weather.getData().getYesterday().getHigh();
        List<Integer> highs = new ArrayList<>(10);
        highs.add(stringHandler.tackleWeather(high));

        List<WeatherSingleDay> forecast = weather.getData().getForecast();
        for (WeatherSingleDay weatherSingleDay : forecast) {
            highs.add(stringHandler.tackleWeather(weatherSingleDay.getHigh()));
        }
        return highs;
    }

}

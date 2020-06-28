package com.entity.weather;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/28 17:22
 */
public class WeatherData {

    private String city;
    private WeatherYesterday yesterday;
    private List<WeatherSingleDay> forecast;
    private String ganmao;
    private String aqi;
    private int wendu;

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public WeatherYesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(WeatherYesterday yesterday) {
        this.yesterday = yesterday;
    }

    public List<WeatherSingleDay> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherSingleDay> forecast) {
        this.forecast = forecast;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public int getWendu() {
        return wendu;
    }

    public void setWendu(int wendu) {
        this.wendu = wendu;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "city='" + city + '\'' +
                ", yesterday=" + yesterday +
                ", forecast=" + forecast +
                ", ganmao='" + ganmao + '\'' +
                ", aqi='" + aqi + '\'' +
                ", wendu=" + wendu +
                '}';
    }
}

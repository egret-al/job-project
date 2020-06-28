package com.function;

import com.entity.authentication.Account;
import com.entity.weather.Weather;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.MailUtils;
import com.utils.StringHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 冉堃赤
 * @date 2020/3/28 17:05
 */
public class testParseJson {

    @Test
    public void testParseJsonString() throws Exception {
        String string = "{'id':'12345','age':18}";
        Account account = new Account();
        account.setPassword("zhangsan");
        account.setPassword("123456");

        ObjectMapper objectMapper = new ObjectMapper();
        //对象 --> json字符串
        String result = objectMapper.writeValueAsString(account);
        System.out.println(result);

        //json字符串 --> 对象
        Account jsonAccount = objectMapper.readValue(result, Account.class);
        System.out.println(jsonAccount);
    }

    /**
     * 测试将天气情况的json字符串转化为weather对象
     * @throws Exception
     */
    @Test
    public void testParseWeather() throws Exception {
//        String condition = "{\"data\":{\"yesterday\":{\"date\":\"27日星期五\",\"high\":\"高温 17℃\",\"fx\":\"北风\",\"low\":\"低温 12℃\",\"fl\":\"<![CDATA[<3级]]>\",\"type\":\"小雨\"},\"city\":\"重庆\",\"forecast\":[{\"date\":\"28日星期六\",\"high\":\"高温 16℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 12℃\",\"fengxiang\":\"西风\",\"type\":\"小雨\"},{\"date\":\"29日星期天\",\"high\":\"高温 18℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 13℃\",\"fengxiang\":\"南风\",\"type\":\"小雨\"},{\"date\":\"30日星期一\",\"high\":\"高温 20℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 13℃\",\"fengxiang\":\"南风\",\"type\":\"阴\"},{\"date\":\"31日星期二\",\"high\":\"高温 24℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 15℃\",\"fengxiang\":\"南风\",\"type\":\"多云\"},{\"date\":\"1日星期三\",\"high\":\"高温 26℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 17℃\",\"fengxiang\":\"东南风\",\"type\":\"多云\"}],\"ganmao\":\"天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。\",\"wendu\":\"13\"},\"status\":1000,\"desc\":\"OK\"}";
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(condition);
//
//        Weather weather = objectMapper.readValue(condition, Weather.class);
//        System.out.println(weather);
        StringHandler stringHandler = new StringHandler();
        int i = stringHandler.tackleWeather("低温 17℃");
        System.out.println(i);
    }
}

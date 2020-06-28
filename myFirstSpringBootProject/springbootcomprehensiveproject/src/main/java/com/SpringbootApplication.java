package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 项目启动类：
 * @ServletComponentScan：servlet组件扫描注解
 * @SpringBootApplication：springboot启动注解
 * @EnableTransactionManagement：开启事务支持
 */
//@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
package com.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/4/20 15:03
 */
@Component("loginFailureHandler")
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFailureHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        LOGGER.error("登录错误 [{}] ", e.getMessage());
        Map<Object, Object> result = new HashMap<>();
        result.put("status", "401");

        if (e instanceof LockedException) {
            result.put("msg", "账户被锁定，登录失败！");
        } else if (e instanceof BadCredentialsException) {
            result.put("msg", "账户或密码输入错误，登录失败！");
        } else if (e instanceof DisabledException) {
            result.put("msg", "账户被禁用，登录失败！");
        } else if (e instanceof AccountExpiredException) {
            result.put("msg", "账户已过期，登录失败！");
        } else if (e instanceof CredentialsExpiredException) {
            result.put("msg", "密码已过期，登录失败！");
        } else {
            result.put("msg", "登录失败！");
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}

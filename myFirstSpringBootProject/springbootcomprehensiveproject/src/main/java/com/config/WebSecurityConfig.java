package com.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interceptor.VerifyCodeFilter;
import com.security.LoginFailureHandler;
import com.security.LoginSuccessHandler;
import com.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring Security的配置类
 * @author 冉堃赤
 * @date 2020/3/9 15:13
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private ObjectMapper objectMapper;
    private UserDetailsService userDetailsService;

//    @Autowired
//    private VerifyCodeFilter verifyCodeFilter;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("user")
//                .password(passwordEncoder.encode("123456")).roles("USER");
//
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("lisi")
//                .password(passwordEncoder.encode("123456")).roles("STUDENT");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/druid/**").antMatchers("/assets/**")
        .antMatchers("/doc/**").antMatchers("/favicon.ico");
    }

    /**
     * 持久化token
     * Security中，默认是使用PersistenTokenRepository的子类InMemoryTokenRepositoryImpl，将
     * token放在内存中，如果使用JdbcTokenRepositoryImpl，会创建persistent_logins，将token持久化到数据库
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //设置数据源
        tokenRepository.setDataSource(dataSource);
        //启动创建表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/forgot-password", "/resetPassword", "/sendVerificationCode",
                        "/registerMerchant", "/sign-up", "/getCode", "/registerMerchantAboutInfo").permitAll()
//                .antMatchers("/**").fullyAuthenticated()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/sign-in").permitAll().loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                //rememberMe
                .and().rememberMe()
                //设置UserDetailService
                .userDetailsService(userDetailsService)
                //设置数据库访问层
                .tokenRepository(persistentTokenRepository())
                //设置记住我的时间，单位：秒
                .tokenValiditySeconds(60 * 1)
                .and().csrf().disable()
                .sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);

        //注销账户（注销账户会自动跳转到登录页面）
//        http.logout().logoutSuccessUrl("/sign-in");

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
//        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(filter, CsrfFilter.class);
    }
}

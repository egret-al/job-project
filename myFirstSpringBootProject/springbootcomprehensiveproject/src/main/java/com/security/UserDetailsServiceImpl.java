package com.security;

import com.entity.authentication.Account;
import com.entity.authentication.Authority;
import com.service.authentication.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/14 11:57
 */
@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询账户
        Account account = accountService.selectById(username);
        if (account != null) {
            //查询拥有的权限
            List<Authority> ownAuthorities = accountService.selectAuthorityByUserName(username);

            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Authority authority : ownAuthorities) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthorityName());
                authorities.add(grantedAuthority);
            }
            //给用户设置权限
            account.setAuthorities(authorities);
            logger.info("当前登录用户：" + account);
            logger.info("拥有的权限：" + account.getAuthorities());
            return account;
        } else {
            throw new UsernameNotFoundException("账户或者密码输入错误！");
        }
    }
}

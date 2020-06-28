package com.service.authentication;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.entity.authentication.Account;
import com.entity.authentication.Authority;
import com.entity.authentication.User;
import com.service.base.BaseMapperService;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 账户业务层接口
 * @author 冉堃赤
 * @date 2020/3/14 11:16
 */
public interface AccountService extends BaseMapperService<Account> {

    int setAuthority(String username, Integer authorityId);

    Account selectUserByUsername(String username);

    int addUserByRegister(User user);

    List<Authority> selectAuthorityByUserName(String username);

    User selectStudentByUserName(String username);
}

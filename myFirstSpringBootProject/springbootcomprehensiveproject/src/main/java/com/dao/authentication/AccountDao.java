package com.dao.authentication;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.authentication.Account;
import com.entity.authentication.Authority;
import com.entity.authentication.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户持久层
 * @author 冉堃赤
 * @date 2020/3/14 11:14
 */
@Mapper
@Repository("accountDao")
public interface AccountDao extends BaseMapper<Account> {

    /**
     * 设置权限
     * @param username 账号
     * @param authorityId 权限id，多对多映射
     * @return 影响行数
     */
    @Insert("INSERT INTO t_account_auth(account_id,auth_id) VALUES(#{username},#{authorityId})")
    int setAuthority(String username, Integer authorityId);

    /**
     * 根据账号查询账户实体类
     * @param username 账号
     * @return 账户类
     */
    @Select("SELECT * FROM t_account WHERE username=#{username}")
    @Results(id = "accountMapper", value = {
            @Result(id = true, column = "username", property = "username"),
            @Result(column = "password", property = "password"),
    })
    Account selectUserByUsername(@Param("username") String username);

    /**
     * 多对多查询，根据用户学号查询所拥有的权限
     * @param username 账号
     * @return 权限列表
     */
    @Select("SELECT au.* FROM t_account a INNER JOIN t_account_auth aa" +
            " ON a.username = aa.account_id INNER JOIN t_auth au ON aa.auth_id = au.id " +
            " WHERE a.username = #{username}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role_name", property = "authorityName"),
            @Result(column = "role_describe", property = "authorityDescription")
    })
    List<Authority> selectAuthorityByUserName(String username);

    /**
     * 根据登录账号获取到当前账号用户
     * @param username 登录账号
     * @return 学生实体类
     */
    @Select("SELECT * FROM t_user WHERE t_account = #{username}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "identification", property = "identification"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "nation", property = "nation"),
            @Result(column = "t_account", property = "account"),
            @Result(column = "bind_mail", property = "email"),
            @Result(column = "major_code", property = "majorCode"),
            @Result(column = "current_address", property = "currentAddress"),
            @Result(column = "subsidiary_organ", property = "subsidiaryOrgan"),
            @Result(column = "phone", property = "phone"),
    })
    User selectStudentByUserName(String username);

    /**
     * 插入用户信息
     * @param user 注册用户
     * @return 影响行数
     */
    @Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "id")
    @Insert("INSERT INTO t_user(name,identification,gender,nation,t_account,bind_mail," +
            "current_address,subsidiary_organ,phone) VALUES(#{user.name},#{user.identification},#{user.gender},#{user.nation}," +
            "#{user.account},#{user.email},#{user.currentAddress},#{user.subsidiaryOrgan},#{user.phone})")
    int addUserByRegister(@Param("user") User user);

    /**
     * 商家注册时关联关系
     * @param accountId 账户
     * @param authId 对应角色id
     * @return 影响行数
     */
    @Insert("INSERT INTO t_account_auth(account_id, auth_id) VALUES(#{acId}, #{auId})")
    int addAccountWithAuth(@Param("acId") String accountId, @Param("auId") int authId);
}

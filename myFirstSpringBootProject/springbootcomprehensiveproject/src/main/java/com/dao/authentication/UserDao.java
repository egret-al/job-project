package com.dao.authentication;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.authentication.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/25 19:43
 */
@Mapper
@Repository("userDao")
public interface UserDao extends BaseMapper<User> {

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return
     */
    @Select("SELECT * FROM t_user WHERE id=#{id}")
    @Results(id = "userMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "identification", property = "identification"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "nation", property = "nation"),
            @Result(column = "bind_mail", property = "email"),
            @Result(column = "current_address", property = "currentAddress"),
            @Result(column = "subsidiary_organ", property = "subsidiaryOrgan"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "t_account", property = "account")
    })
    User selectUserById(Integer id);

    /**
     * 传入一类权限，获取该权限的所有人
     * @param person 权限名
     * @return 集合
     */
    @ResultMap("userMapper")
    @Select("SELECT u.* FROM t_user u INNER JOIN t_account a ON u.t_account=a.username " +
            "INNER JOIN t_account_auth aa ON a.username=aa.account_id " +
            "INNER JOIN t_auth au ON aa.auth_id=au.id " +
            "WHERE au.role_name=#{person}")
    List<User> selectAllByRoleName(String person);
}

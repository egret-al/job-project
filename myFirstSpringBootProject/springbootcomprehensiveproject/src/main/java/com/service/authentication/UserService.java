package com.service.authentication;

import com.entity.authentication.Account;
import com.entity.authentication.User;
import com.entity.student.RegisterStudentBean;
import com.exception.StudentExistException;
import com.service.base.BaseMapperService;
import com.utils.RoleEnum;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/25 19:44
 */
public interface UserService extends BaseMapperService<User> {

    int modifyStudentPassword(String username, String password);

    int modifyStudentInfo(String newEmail, String newName, String newPhone, String newAddress, String sId) throws CloneNotSupportedException;

    int registerStudent(RegisterStudentBean registerStudentBean) throws StudentExistException;

    List<User> selectAllByRoleName(RoleEnum roleEnum);

    User selectUserById(Integer id);
}

package com.service.authentication.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.authentication.AccountDao;
import com.dao.authentication.UserDao;
import com.dao.payment.BankCardDao;
import com.entity.authentication.Account;
import com.entity.authentication.User;
import com.entity.payment.BankCard;
import com.entity.student.RegisterStudentBean;
import com.exception.BankCardRepeatedException;
import com.exception.StudentExistException;
import com.service.authentication.UserService;
import com.service.merchant.JobService;
import com.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 冉堃赤
 * @date 2020/3/25 19:44
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private BankCardDao bankCardDao;

    @Autowired
    private JobService jobService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(User entity) {
        return userDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return userDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return userDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<User> wrapper) {
        return userDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return userDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(User entity) {
        return userDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(User entity, Wrapper<User> updateWrapper) {
        return userDao.update(entity, updateWrapper);
    }

    @Override
    public User selectById(Serializable id) {
        return userDao.selectById(id);
    }

    @Override
    public List<User> selectBatchIds(Collection<? extends Serializable> idList) {
        return userDao.selectBatchIds(idList);
    }

    @Override
    public List<User> selectByMap(Map<String, Object> columnMap) {
        return userDao.selectByMap(columnMap);
    }

    @Override
    public User selectOne(Wrapper<User> queryWrapper) {
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<User> queryWrapper) {
        return userDao.selectCount(queryWrapper);
    }

    @Override
    public List<User> selectList(Wrapper<User> queryWrapper) {
        return userDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<User> queryWrapper) {
        return userDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<User> queryWrapper) {
        return userDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<User> selectPage(IPage<User> page, Wrapper<User> queryWrapper) {
        return userDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<User> page, Wrapper<User> queryWrapper) {
        return userDao.selectMapsPage(page, queryWrapper);
    }

    /**
     * 修改学生密码
     * @param username 账号
     * @param password 明文密码
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modifyStudentPassword(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        return accountDao.updateById(account);
    }

    /**
     * 修改学生信息
     * @param newEmail 新邮箱
     * @param newName 新名字
     * @param newPhone 新手机号码
     * @param newAddress 新地址
     * @param sId 学生id
     * @return 影响行数
     * @throws CloneNotSupportedException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modifyStudentInfo(String newEmail, String newName, String newPhone, String newAddress, String sId) throws CloneNotSupportedException {
        User student = userDao.selectOne(new QueryWrapper<User>().eq("id", sId));
        User fresh = (User) student.clone();
        fresh.setEmail(newEmail);
        fresh.setName(newName);
        fresh.setPhone(newPhone);
        fresh.setCurrentAddress(newAddress);

        return userDao.updateById(fresh);
    }

    /**
     * 注册学生学籍
     * @param registerStudentBean
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerStudent(RegisterStudentBean registerStudentBean) throws StudentExistException {
        BankCard card = bankCardDao.selectById(registerStudentBean.getCreditCard());
        if (card != null) {
            //已经被注册过
            throw new BankCardRepeatedException("银行卡已经被注册！");
        } else {
            List<Account> accounts = accountDao.selectList(new QueryWrapper<Account>().eq("username",
                    registerStudentBean.getStudentNum()));

            if (accounts.size() > 0) {
                throw new StudentExistException("学号重复注册，注册失败");
            } else {

                //对银行卡号进行简单处理
                registerStudentBean.setCreditCard(registerStudentBean.getCreditCard().replace(" ", ""));
                System.out.println(registerStudentBean);

                //添加登录账号信息
                Account account = new Account();
                account.setUsername(registerStudentBean.getStudentNum());
                account.setPassword(passwordEncoder.encode(registerStudentBean.getPassword()));

                int r1 = accountDao.insert(account);

                //添加学生信息
                User student = new User();
                student.setName(registerStudentBean.getRealName());
                student.setPhone(registerStudentBean.getPhone());
                student.setCurrentAddress(registerStudentBean.getHomeAddress());
                student.setEmail(registerStudentBean.getBindEmail());
                student.setGender(registerStudentBean.getSex());
                student.setIdentification(registerStudentBean.getIdCard());
                student.setNation(registerStudentBean.getNation());
                student.setAccount(account.getUsername());
                student.setSubsidiaryOrgan(registerStudentBean.getAcademy());
                int r2 = accountDao.addUserByRegister(student);

                //注册银行卡
                BankCard bankCard = new BankCard(registerStudentBean.getCreditCard(), 0, student.getId());

                int r4 = bankCardDao.insert(bankCard);
                //设置学生权限
                int r3 = accountDao.setAuthority(account.getUsername(), 2);

                return r1 + r2 + r3 + r4;
            }
        }
    }

    @Override
    public List<User> selectAllByRoleName(RoleEnum roleEnum) {
        return userDao.selectAllByRoleName(roleEnum.getRoleName());
    }

    @Override
    public User selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }
}

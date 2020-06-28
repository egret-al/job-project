package com.service.authentication.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.authentication.AccountDao;
import com.entity.authentication.Account;
import com.entity.authentication.Authority;
import com.entity.authentication.User;
import com.exception.AccountExistException;
import com.service.authentication.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 账户业务层实现类
 * @author 冉堃赤
 * @date 2020/3/14 11:19
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int setAuthority(String username, Integer authorityId) {
        return accountDao.setAuthority(username, authorityId);
    }

    @Override
    public Account selectUserByUsername(String username) {
        return accountDao.selectUserByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUserByRegister(User user) {
        return accountDao.addUserByRegister(user);
    }

    @Override
    public List<Authority> selectAuthorityByUserName(String username) {
        return accountDao.selectAuthorityByUserName(username);
    }

    @Override
    public User selectStudentByUserName(String username) {
        return accountDao.selectStudentByUserName(username);
    }

    /**
     * 判断是校内商家注册还是校外商家注册或者是学校官方为学生注册
     * @param account 账户
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Account account) {
        Account temp = accountDao.selectById(account.getUsername());
        int result1 = 0, result2 = 0;
        if (temp == null) {
            //加密密码
            account.setPassword(passwordEncoder.encode(account.getPassword()));

            char[] chars = account.getUsername().toCharArray();
            if (chars[0] == 'I') {
                //校内商家正在注册账号
                result1 = accountDao.insert(account);
                result2 = accountDao.addAccountWithAuth(account.getUsername(), 4);
            } else if (chars[0] == 'O') {
                //校外商家正在注册账号
                result1 = accountDao.insert(account);
                result2 = accountDao.addAccountWithAuth(account.getUsername(), 5);
            } else {
                //学生账号
                result1 = accountDao.insert(account);
                result2 = accountDao.addAccountWithAuth(account.getUsername(), 2);
            }
        } else {
            throw new AccountExistException("该账户已经存在，注册失败！");
        }
        return result1 + result2;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable username) {
        return accountDao.deleteById(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return accountDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<Account> wrapper) {
        return accountDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return accountDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(Account account) {
        return accountDao.updateById(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Account account, Wrapper<Account> updateWrapper) {
        return accountDao.update(account, updateWrapper);
    }

    @Override
    public Account selectById(Serializable id) {
        return accountDao.selectById(id);
    }

    @Override
    public List<Account> selectBatchIds(Collection<? extends Serializable> idList) {
        return accountDao.selectBatchIds(idList);
    }

    @Override
    public List<Account> selectByMap(Map<String, Object> columnMap) {
        return accountDao.selectByMap(columnMap);
    }

    @Override
    public Account selectOne(Wrapper<Account> queryWrapper) {
        return accountDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<Account> queryWrapper) {
        return accountDao.selectCount(queryWrapper);
    }

    @Override
    public List<Account> selectList(Wrapper<Account> queryWrapper) {
        return accountDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<Account> queryWrapper) {
        return accountDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<Account> queryWrapper) {
        return accountDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<Account> selectPage(IPage<Account> page, Wrapper<Account> queryWrapper) {
        return accountDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<Account> page, Wrapper<Account> queryWrapper) {
        return accountDao.selectMapsPage(page, queryWrapper);
    }
}

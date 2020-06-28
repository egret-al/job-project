package com;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.authentication.AccountDao;
import com.entity.authentication.Account;
import com.entity.authentication.Authority;
import com.entity.authentication.User;
import com.service.authentication.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * 持久层单元测试
 * @author 冉堃赤
 * @date 2020/3/7 12:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestDao {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDao accountDao;

    @Test
    public void testAddMerchant() throws Exception {
        Account account = new Account();
        account.setUsername("1122334455");
        account.setPassword(new BCryptPasswordEncoder().encode("123456"));
        int insert = accountDao.insert(account);
        System.out.println(insert);

//        int auth = accountDao.addAccountWithAuth(account.getUsername(), 5);
//        System.out.println(auth);
    }

    /**
     * 添加账户测试单元（哈希加密）
     * @throws Exception
     */
    @Test
    public void testAccountInsert() throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Account account = new Account();
        account.setUsername("2018120000");
        account.setPassword(passwordEncoder.encode("123456"));

        accountService.insert(account);
    }

    /**
     * 测试账户修改
     * @throws Exception
     */
    @Test
    public void testAccountUpdate() throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Account account = new Account();
        account.setUsername("2018120000");
        account.setPassword(passwordEncoder.encode("1111"));

        int result = accountService.update(account, new QueryWrapper<Account>().eq("username", account.getUsername()));
        System.out.println(result);
    }

    @Test
    public void testAccountSelect() throws Exception {
        Account account = accountService.selectById("2018120000");
        System.out.println("123456".equals(account.getPassword()));
    }

    /**
     * 测试根据学号获取用户权限列表
     * @throws Exception
     */
    @Test
    public void testAuthority() throws Exception {
        List<Authority> authorities = accountService.selectAuthorityByUserName("2018120000");
        for (Authority authority : authorities) {
            System.out.println(authority);
        }
    }

    @Test
    public void testSelectStudentByUserName() throws Exception {
        User user = accountService.selectStudentByUserName("2018120000");
        System.out.println(user);
    }
}

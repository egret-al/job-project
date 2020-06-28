package com.user;

import com.SpringbootApplication;
import com.entity.authentication.User;
import com.service.authentication.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/25 19:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestUser {

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() throws Exception {
    }

    @Test
    public void testSelectAllUser() throws Exception {
        List<User> users = userService.selectList(null);
        users.forEach(System.out::println);

    }

    @Test
    public void testSelectUserById() throws Exception {
        User user = userService.selectUserById(2);
        System.out.println(user);
    }

}

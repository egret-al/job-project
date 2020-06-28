package com.myEnum;

import com.SpringbootApplication;
import com.entity.authentication.User;
import com.service.authentication.UserService;
import com.utils.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/4/3 10:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class EnumTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSelectAllByEnum() throws Exception {
        List<User> users = userService.selectAllByRoleName(RoleEnum.O_MERCHANT);
        users.forEach(System.out::println);
    }
}

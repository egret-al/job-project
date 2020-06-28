package com.controller.function;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.authentication.Account;
import com.entity.authentication.RegisterMerchant;
import com.entity.authentication.User;
import com.entity.function.ForgotPassword;
import com.entity.merchant.Job;
import com.entity.merchant.JobReleaseStatus;
import com.entity.student.Resume;
import com.exception.AccountExistException;
import com.service.authentication.AccountService;
import com.service.authentication.UserService;
import com.service.merchant.JobReleaseStatusService;
import com.service.merchant.JobService;
import com.service.student.ResumeService;
import com.utils.MailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * 功能模块的控制器
 * @author 冉堃赤
 * @date 2020/3/21 9:42
 */
@Controller
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class FunctionController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobReleaseStatusService jobReleaseStatusService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResumeService resumeService;

    private String code;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${file-server-location}")
    private String fileServerLocation;

    /**
     * 发送邮箱验证码
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/sendVerificationCode", method = RequestMethod.POST)
    public Object sendVerificationCode(@RequestParam("account") String account, @RequestParam("email") String email) {
        //查询数据库，对比邮箱是否相同，如果不同返回状态码0，相同则返回状态码1且发送邮箱验证码，不存在该用户则返回-1
        User user = accountService.selectStudentByUserName(account);
        Map<Object, Object> result = new HashMap<>(10);

        if (user != null) {
            if (!user.getEmail().equals(email)) {
                result.put("status", 0);
                result.put("operation", false);

            } else {
                result.put("status", 1);
                //发送验证码
                Random random = new Random();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i <= 6; i++) {
                    stringBuilder.append(random.nextInt(10));
                }
                code = stringBuilder.toString();
                String content = "您正在执行忘记密码的操作，您的验证码为" + stringBuilder.toString();
                mailUtils.sendSimpleMail(user.getEmail(), "找回密码", content);
                result.put("operation", true);
            }
        } else {
            result.put("status", -1);
        }
        return result;
    }

    /**
     * 重置密码
     * @return json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Object resetPassword(@RequestBody ForgotPassword forgotPassword) {
        Map<Object, Object> result = new HashMap<>(10);
        //验证码正确返回1，验证码错误返回0
        if (forgotPassword.getVerificationCode().equals(code)) {
            //验证码正确
            result.put("status", 1);
            //查询数据库
            Account account = new Account();
            account.setUsername(forgotPassword.getAccount());
            //单向加密
            account.setPassword(passwordEncoder.encode(forgotPassword.getNewPassword()));

            int influence = accountService.update(account, new QueryWrapper<Account>().eq("username", account.getUsername()));
            if (influence > 0) {
                logger.info("修改成功");
                result.put("msg", "修改成功！");
            } else {
                logger.warn("修改失败");
                result.put("msg", "修改失败！");
            }
        } else {
            result.put("status", 0);
            result.put("msg", "验证码输入错误！");
        }
        return result;
    }

    /**
     * 修改密码
     * @param newPassword 新密码
     * @param session 会话
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
    public Object updatePassword(@RequestParam String newPassword, HttpSession session) {
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Map<Object, Object> result = new HashMap<>(10);
        Account account = (Account) securityContext.getAuthentication().getPrincipal();
        //重置密码
        account.setPassword(passwordEncoder.encode(newPassword));
        int influence = accountService.update(account, new QueryWrapper<Account>().eq("username", account.getUsername()));
        System.out.println(influence);
        if (influence > 0) {
            result.put("status", 1);
        } else {
            result.put("status", 0);
        }

        return result;
    }

    /**
     * 修改信息
     * @param email 邮箱
     * @param phone 手机电话
     * @param subsidiaryOrgan 附属机构
     * @param address 地址
     * @param session 会话
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
    public Object updateUser(@RequestParam("email") String email, @RequestParam("phone") String phone
            , @RequestParam("subsidiaryOrgan") String subsidiaryOrgan, @RequestParam("address") String address, HttpSession session) {
        Map<Object, Object> result = new HashMap<>(10);
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        //修改的信息
        User user = (User) session.getAttribute("loginUser");
        user.setEmail(email);
        user.setPhone(phone);
        user.setSubsidiaryOrgan(subsidiaryOrgan);
        user.setCurrentAddress(address);

        int influence = userService.updateById(user);
        if (influence > 0) {
            result.put("status", 1);
        } else {
            result.put("status", 0);
        }
        return result;
    }
}

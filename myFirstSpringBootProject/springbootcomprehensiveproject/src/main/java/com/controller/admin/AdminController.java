package com.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.authentication.Account;
import com.entity.authentication.User;
import com.entity.merchant.Job;
import com.entity.merchant.JobReleaseStatus;
import com.entity.merchant.TradingCertificate;
import com.entity.other.Comment;
import com.entity.student.RegisterStudentBean;
import com.exception.BankCardRepeatedException;
import com.exception.StudentExistException;
import com.service.authentication.AccountService;
import com.service.authentication.UserService;
import com.service.merchant.JobReleaseStatusService;
import com.service.merchant.JobService;
import com.service.merchant.TradingCertificateService;
import com.service.other.CommentService;
import com.service.other.CreditGradeService;
import com.service.payment.BankCardService;
import com.utils.CommonFunction;
import com.utils.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 冉堃赤
 * @date 2020/4/3 10:35
 */
@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TradingCertificateService tradingCertificateService;

    @Autowired
    private JobService jobService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CreditGradeService creditGradeService;

    @Autowired
    private CommonFunction commonFunction;

    @Autowired
    private CommentService commentService;

    @Autowired
    private JobReleaseStatusService jobReleaseStatusService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 同意该岗位
     * @param jobId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/consent/job", method = RequestMethod.POST)
    public Object consentJob(@RequestParam int jobId) {
        Job job = jobService.selectById(jobId);
        job.setCurrentStatus(1);

        int status = jobService.update(job, new QueryWrapper<Job>().eq("id", job.getId()));
        if (status > 0) {
            JobReleaseStatus jobReleaseStatus = new JobReleaseStatus();
            jobReleaseStatus.setJobId(jobId);
            jobReleaseStatus.setIsPass(1);
            jobReleaseStatus.setHandleTime(new Date());
            jobReleaseStatus.setExplainContent("该审核通过");

            int insert = jobReleaseStatusService.insert(jobReleaseStatus);
            System.out.println(insert);
        }

        Map<Object, Object> result = new HashMap<>(10);

        result.put("status", 1);
        return result;
    }

    /**
     * 拒绝该岗位
     * @param jobId 岗位id
     * @param reason 原因
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/refuse/job", method = RequestMethod.POST)
    public Object refuseJob(@RequestParam int jobId, @RequestParam String reason) {
        Job job = jobService.selectById(jobId);
        //拒绝申请
        job.setCurrentStatus(-1);

        int status = jobService.update(job, new QueryWrapper<Job>().eq("id", job.getId()));
        if (status > 0) {
            JobReleaseStatus jobReleaseStatus = new JobReleaseStatus();
            jobReleaseStatus.setJobId(jobId);
            jobReleaseStatus.setIsPass(0);
            jobReleaseStatus.setHandleTime(new Date());
            jobReleaseStatus.setExplainContent(reason);

            int insert = jobReleaseStatusService.insert(jobReleaseStatus);
            System.out.println(insert);
        }

        Map<Object, Object> result = new HashMap<>(10);

        result.put("status", 1);
        return result;
    }

    /**
     * 查询所有学生
     * @return
     */
    @RequestMapping("/admin/list-student")
    public ModelAndView listStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/list-student");

        //查询所有学生
        List<User> studentList = userService.selectAllByRoleName(RoleEnum.STUDENT);
        modelAndView.addObject("studentList", studentList);

        return modelAndView;
    }

    /**
     * 查询所有学生
     * @return
     */
    @RequestMapping("/admin/list-merchant")
    public ModelAndView listMerchant() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/list-merchant");

        List<User> oMerchantList = userService.selectAllByRoleName(RoleEnum.O_MERCHANT);
        List<User> iMerchantList = userService.selectAllByRoleName(RoleEnum.I_MERCHANT);
        List<User> merchantList = new ArrayList<>(10);
        merchantList.addAll(oMerchantList);
        merchantList.addAll(iMerchantList);
        modelAndView.addObject("merchantList", merchantList);

        return modelAndView;
    }

    /**
     * 查看单个学生的信息
     * @param id
     * @return
     */
    @RequestMapping("/admin/student-details")
    public ModelAndView studentDetails(@RequestParam("sId") String id) {
        User user = userService.selectById(id);
        ModelAndView modelAndView = new ModelAndView("admin/student-details");
        modelAndView.addObject("student", user);

        Account account = accountService.selectById(user.getAccount());
        modelAndView.addObject("account", account);

        return modelAndView;
    }

    /**
     * 管理员查看所有工作详情及状态
     * @return apply页面
     */
    @RequestMapping("/admin/list-job")
    public ModelAndView apply() {
        ModelAndView modelAndView = new ModelAndView("admin/list-job");

        //查询所有的工作岗位以及当前的状态
        List<Job> jobs = jobService.selectAllJob();
        modelAndView.addObject("jobs", jobs);

        return modelAndView;
    }

    /**
     * 管理员查看工作详情
     * @param request
     * @return
     */
    @RequestMapping("/admin/adminJob")
    public ModelAndView adminJobViewDetails(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("admin/admin-job-details");

        Integer jobId = Integer.valueOf(request.getParameter("jobId"));
        Job job = jobService.selectById(jobId);
        modelAndView.addObject("job", job);

        User user = userService.selectOne(new QueryWrapper<User>().eq("t_account", job.getUsername()));
        modelAndView.addObject("currentMerchant", user);

        //获取上传的所有证书
        List<TradingCertificate> tradingCertificateList = tradingCertificateService
                .selectList(new QueryWrapper<TradingCertificate>().eq("uid", user.getId()));
        //将证书集合放入数据中
        modelAndView.addObject("tradingCertificateList", tradingCertificateList);


        //查询该工作的评分
        Map<String, Integer> scoreWithCount = commonFunction.getAvgScoreByJobId(jobId);
        modelAndView.addObject("avgScore", scoreWithCount.get("avgScore"));
        modelAndView.addObject("countWithScore", scoreWithCount.get("countWithScore"));

        //查询该工作的评论
        List<Comment> comments = commentService.selectCommentByJobId(jobId);
        modelAndView.addObject("comments", comments);

        return modelAndView;
    }

    /**
     * 查看单个商家的信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/merchant-details")
    public ModelAndView merchantDetails(@RequestParam("mId") String id) {
        ModelAndView modelAndView = new ModelAndView("admin/merchant-details");

        User user = userService.selectById(id);
        List<TradingCertificate> tradingCertificates = tradingCertificateService.selectList(new QueryWrapper<TradingCertificate>()
                .eq("uid", user.getId()));

        List<Job> releaseJobList = jobService.selectList(new QueryWrapper<Job>().eq("username", user.getAccount()));

        modelAndView.addObject("releaseJobList", releaseJobList);
        modelAndView.addObject("merchant", user);
        modelAndView.addObject("tradingCertificateList", tradingCertificates);

        return modelAndView;
    }

    /**
     * 转到注册学籍的页面
     * @return
     */
    @RequestMapping("/admin/add-student")
    public ModelAndView addStudent() {
        return new ModelAndView("/admin/add-student");
    }

    /**
     * 注册学生
     * @param registerStudentBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin/registerStudent", method = RequestMethod.POST)
    public Object registerStudent(@RequestBody RegisterStudentBean registerStudentBean) {
        Map<Object, Object> map = new HashMap<Object, Object>(10);
        try {
            int influence = userService.registerStudent(registerStudentBean);
            logger.info("注册成功！影响行数：" + influence);
            map.put("status", 1);
            map.put("msg", "注册成功！");
        } catch (StudentExistException e) {
            logger.error(e.getMessage());
            map.put("status", 0);
            map.put("msg", e.getMessage());
        } catch (BankCardRepeatedException e) {
            logger.error(e.getMessage());
            map.put("status", 0);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    /**
     * 修改学生信息
     * @param newEmail 新邮箱
     * @param newName 新姓名
     * @param newPhone 新电话
     * @param newAddress 新地址
     * @param sId 被修改的学生id
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin/modifyStudentInfo", method = RequestMethod.POST)
    public Object modifyStudentInfo(@RequestParam String newEmail, @RequestParam String newName
            , @RequestParam String newPhone, @RequestParam String newAddress, @RequestParam String sId, HttpSession session) {
        Map<Object, Object> map = new HashMap<Object, Object>(10);
        int result = -1;
        try {
            result = userService.modifyStudentInfo(newEmail, newName, newPhone, newAddress, sId);
            if (result >= 0) {
                map.put("state", 1);
                map.put("msg", "修改成功！");
            } else {
                map.put("state", 0);
                map.put("msg", "未知异常！");
            }
        } catch (CloneNotSupportedException e) {
            map.put("state", 0);
            map.put("msg", "服务器异常！");
            logger.error(e.getMessage());
        }

        return map;
    }

    /**
     * 修改学生密码
     * @param newPassword 新密码
     * @param username 新密码
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin/modifyStudentPassword", method = RequestMethod.POST)
    public Object modifyStudentPassword(@RequestParam String newPassword, @RequestParam String username) {
        Map<Object, Object> map = new HashMap<Object, Object>();

        if (userService.modifyStudentPassword(username, newPassword) >= 0) {
            map.put("state", 1);
            map.put("msg", "修改完成！");
        } else {
            map.put("state", 0);
            map.put("msg", "发生异常，修改失败");
        }

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/deleteCommentById", method = RequestMethod.POST)
    public Object deleteCommentUsingAdmin(@RequestParam("commentId") Integer commentId) {
        Map<Object, Object> result = new HashMap<Object, Object>();

        if (commentService.deleteCommentById(commentId) > 0) {
            result.put("status", 1);
            result.put("msg", "删除成功！");
        } else {
            result.put("status", 0);
            result.put("msg", "删除失败！id：" + commentId);
        }

        return result;
    }
}

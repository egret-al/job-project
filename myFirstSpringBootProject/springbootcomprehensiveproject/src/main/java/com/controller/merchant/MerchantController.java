package com.controller.merchant;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.authentication.Account;
import com.entity.authentication.RegisterMerchant;
import com.entity.authentication.User;
import com.entity.merchant.Job;
import com.entity.merchant.JobReleaseStatus;
import com.entity.payment.BankCard;
import com.entity.student.Resume;
import com.exception.AccountExistException;
import com.service.authentication.AccountService;
import com.service.merchant.JobReleaseStatusService;
import com.service.merchant.JobService;
import com.service.other.CreditGradeService;
import com.service.payment.BankCardService;
import com.service.student.ResumeService;
import com.utils.CommonFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/22 9:11
 */
@Controller
public class MerchantController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobReleaseStatusService jobReleaseStatusService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private CreditGradeService creditGradeService;

    @Autowired
    private CommonFunction commonFunction;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BankCardService bankCardService;

    /**
     * 仅有商家才能进入该页面
     * @return 发布工作的页面
     */
    @RequestMapping("/merchant/release-job")
    @PreAuthorize("hasAnyAuthority('O_MERCHANT','I_MERCHANT')")
    public String releaseJob() {
        return "merchant/release-job";
    }

    /**
     * 商家查看自己发布岗位的审核状态
     * @return
     */
    @RequestMapping("/merchant/wait-check")
    @PreAuthorize("hasAnyAuthority('O_MERCHANT','I_MERCHANT')")
    public ModelAndView waitCheck(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("merchant/wait-check");

        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Account account = (Account) context.getAuthentication().getPrincipal();

        List<Job> jobs = jobService.selectJobByUsername(account.getUsername());
        modelAndView.addObject("jobs", jobs);

        return modelAndView;
    }

    /**
     * 查询该工作发布详情
     * @param request
     * @return
     */
    @RequestMapping("/jobDetial/details")
    @PreAuthorize("hasAnyAuthority('O_MERCHANT','I_MERCHANT')")
    public Object jobDetails(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("merchant/job-detail");

        Integer jobId = Integer.valueOf(request.getParameter("jobId"));
        Job job = jobService.selectJobWithCascadeById(jobId);

        List<JobReleaseStatus> list = jobReleaseStatusService.selectList(new QueryWrapper<JobReleaseStatus>()
                .eq("job_id", jobId));

        //获取投递当前岗位的简历的信息
        List<Resume> resumes = resumeService.selectWaitCheckResumeByJid(jobId);

        //将审核后的状态放入modelandview
        if (list.size() > 0) {
            modelAndView.addObject("jobStatus", list);
        }

        //如果有简历投递，则添加到modelandview
        if (resumes.size() > 0) {
            modelAndView.addObject("resumes", resumes);
        }
        modelAndView.addObject("job", job);


        //查询该工作的评分
        Map<String, Integer> scoreWithCount = commonFunction.getAvgScoreByJobId(jobId);
        modelAndView.addObject("avgScore", scoreWithCount.get("avgScore"));
        modelAndView.addObject("countWithScore", scoreWithCount.get("countWithScore"));

        return modelAndView;
    }

    /**
     * 发布岗位申请
     * @param job
     * @param session
     * @return
     */
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('O_MERCHANT','I_MERCHANT')")
    @RequestMapping(value = "/merchant/addReleaseJob", method = RequestMethod.POST)
    public Object addReleaseJob(@RequestBody Job job, HttpSession session) {
        job.setReleaseTime(new Date());
        System.out.println(job);
        Map<Object, Object> result = new HashMap<>(10);
        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Account account = (Account) context.getAuthentication().getPrincipal();
        System.out.println(account);

        int i = jobService.addReleaseJob(job, account.getUsername());
        if (i > 0) {
            result.put("status", 1);
        } else {
            result.put("status", 0);
        }
        return result;
    }

    /**
     * 商家注册，以便后续能够发布职位
     * 状态码：校内商家注册成功为1，校外商家注册成功为2，注册失败为0，商家工号不符合要求（校内I开头，校外O开头）为-1
     * @param registerMerchant
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/registerMerchantAboutInfo", method = RequestMethod.POST)
    public Object registerMerchant(@RequestBody RegisterMerchant registerMerchant) {
        Map<Object, Object> result = new HashMap<Object, Object>(10);
        if (registerMerchant.getNumber() == "" || registerMerchant.getNumber() == null) {
            result.put("status", -1);
            result.put("msg", "操作异常！");
        } else {
            System.out.println(registerMerchant);
            //构建一个account对象，以便后续的添加操作
            Account account = new Account();
            account.setUsername(registerMerchant.getNumber());
            account.setPassword(registerMerchant.getPassword());

            try {
                char[] chars = registerMerchant.getNumber().toCharArray();
                if (chars[0] == 'I') {
                    result.put("status", 1);
                    result.put("msg", "校内商家注册账号成功！");
                } else if (chars[0] == 'O') {
                    result.put("status", 2);
                    result.put("msg", "校外商家注册账号成功！");
                } else {
                    result.put("status", 0);
                    result.put("msg", "注册失败！");
                }
                if (accountService.insert(account) > 0) {
                    User user = new User(registerMerchant.getRealName(), registerMerchant.getIdentification(),
                            registerMerchant.getSex(), registerMerchant.getNation(), registerMerchant.getNumber(),
                            registerMerchant.getBindEmail(), registerMerchant.getCurrentAddress(),
                            registerMerchant.getOrgan(), registerMerchant.getPhone());
                    int i = accountService.addUserByRegister(user);
                    System.out.println(i);
                    //添加银行卡，逻辑简单点，设置余额1w
                    BankCard bankCard = new BankCard(registerMerchant.getBank(), 100000, user.getId());
                    bankCardService.insert(bankCard);
                }
            } catch (AccountExistException e) {
                result.put("error", "注册失败，该账户已经存在！");
            }
        }
        return result;
    }

    /**
     * 跳转到发工资的页面
     * @return
     */
    @RequestMapping("/merchant/issus-salary")
    @PreAuthorize("hasAnyAuthority('O_MERCHANT','I_MERCHANT')")
    public ModelAndView issusSalary(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("merchant/issus-salary");
        //获取到商家信息
        Account account = (Account) request.getSession().getAttribute("account");

        List<Resume> resumeList = resumeService.selectAllPassedResumeByUsername(account.getUsername());
        modelAndView.addObject("resumeList", resumeList);

        return modelAndView;
    }

    /**
     * 只有拥有商家权利的才能同意学生的简历
     * @param resumeId
     * @param jobId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/merchant/agree")
    @PreAuthorize("hasAnyAuthority('O_MERCHANT','I_MERCHANT')")
    public Object agreeResume(@RequestParam("rId") Integer resumeId, @RequestParam("jId") Integer jobId) {
        int influence = resumeService.agreeStudentResume(resumeId, jobId);
        Map<Object, Object> result = new HashMap<>(10);
        if (influence > 0) {
            result.put("status", 1);
            result.put("msg", "操作成功！");
        } else if (influence == -2) {
            result.put("status", -2);
            result.put("msg", "已经招收完成，操作失败！");
        } else {
            result.put("status", 0);
            result.put("msg", "操作成功！");
        }
        return result;
    }

    /**
     * 只有拥有商家权利的才能拒绝学生简历
     * @param resumeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/merchant/reject")
    @PreAuthorize("hasAnyAuthority('O_MERCHANT','I_MERCHANT')")
    public Object rejectResume(@RequestParam("rId") String resumeId) {
        int influence = resumeService.rejectStudentResume(resumeId);
        Map<Object, Object> result = new HashMap<>(10);
        if (influence > 0) {
            result.put("status", 1);
            result.put("msg", "操作成功！");
        } else {
            result.put("status", 0);
        }
        return result;
    }
}

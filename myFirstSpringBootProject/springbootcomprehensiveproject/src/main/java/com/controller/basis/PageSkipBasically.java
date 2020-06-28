package com.controller.basis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.authentication.Account;
import com.entity.authentication.User;
import com.entity.coronavirus.CoronavirusData;
import com.entity.merchant.Job;
import com.entity.merchant.TradingCertificate;
import com.entity.other.Comment;
import com.entity.student.Resume;
import com.entity.weather.Weather;
import com.exception.ServerOccurredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.authentication.AccountService;
import com.service.authentication.UserService;
import com.service.coronavirus.CoronavirusDataService;
import com.service.merchant.JobReleaseStatusService;
import com.service.merchant.JobService;
import com.service.merchant.TradingCertificateService;
import com.service.other.CommentService;
import com.service.other.CreditGradeService;
import com.service.payment.BankCardService;
import com.service.student.ResumeService;
import com.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author 冉堃赤
 * @date 2020/3/11 16:41
 */
@Controller
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8",
    name = "globalConfig"
)
public class PageSkipBasically {

    @Value("${city-name}")
    private String cityName;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringHandler stringHandler;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CoronavirusDataService coronavirusDataService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobReleaseStatusService jobReleaseStatusService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private WeatherUtils weatherUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private UserService userService;

    @Autowired
    private TradingCertificateService tradingCertificateService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CreditGradeService creditGradeService;

    @Autowired
    private CommonFunction commonFunction;

    /**
     * 直接登录的方式
     * @return index页面
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String indexByForm(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Account account = (Account) context.getAuthentication().getPrincipal();
        logger.info("用户登录成功：" + account);
        return "user/index";
    }

    /**
     * 验证码
     * @param request
     * @return
     */
    @GetMapping("/getCode")
    public Object getCode(HttpServletRequest request) {

        /* 生成验证码字符串 */
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = UUIDUtils.GeneratorUUIDOfSimple();

        HttpSession session = request.getSession();
        session.setAttribute(uuid,verifyCode);

        int w = 111, h = 36;

        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
            return new BaseResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMessage(),
                    new ImgVO("data:image/gif;base64," + Base64Utils.encodeToString(stream.toByteArray()), uuid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ajax异步登录的方式
     * @return index页面
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView indexByAjax(HttpServletRequest request, HttpSession session, Model model) {
        try {
            ModelAndView modelAndView = new ModelAndView();

            SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Account account = (Account) context.getAuthentication().getPrincipal();
            logger.info("用户登录成功：" + account);

            User user = accountService.selectStudentByUserName(account.getUsername());
            logger.info("登录人信息：" + user);

            //获取登录用户的所有权限
            Collection<? extends GrantedAuthority> authorities = account.getAuthorities();
            //创建4个基本权限
            GrantedAuthority IMERCHANT = new SimpleGrantedAuthority("I_MERCHANT");
            GrantedAuthority OMERCHANT = new SimpleGrantedAuthority("O_MERCHANT");
            GrantedAuthority ADMIN = new SimpleGrantedAuthority("ADMIN");
            GrantedAuthority STUDENT = new SimpleGrantedAuthority("STUDENT");

            //如果是学生登录，那么将申请简历的信息添加到modelandview，如果是商家，则将工作状态添加到modelandview
            if (authorities.contains(STUDENT)) {
                //查询该学生申请的所有简历
                List<Resume> resumeList = resumeService.selectResumeBySid(user.getId());
                model.addAttribute("resumes", resumeList);
                modelAndView.setViewName("user/index");

                //获取天气预报信息的字符串      优先查询默认城市
                String weatherCondition = weatherUtils.getWeatherCondition(cityName);
                if (!weatherCondition.contains("error")) {
                    //解析该字符串为weather对象
                    Weather weather = objectMapper.readValue(weatherCondition, Weather.class);

                    //将相关信息添加到model中，以便于thymeleaf获取数据
                    System.out.println(weather);
                    model.addAttribute("weathers", weather);
                }

                session.setAttribute("flag", "student");
            } else if (authorities.contains(OMERCHANT) || authorities.contains(IMERCHANT)){
                //如果是商家登录，则将工作数据放入modelandview
                List<Job> jobs = jobService.selectList(new QueryWrapper<Job>().eq("username", account.getUsername()));
                model.addAttribute("jobs", jobs);
                modelAndView.setViewName("user/index-merchant");
                session.setAttribute("flag", "merchant");
            } else if (authorities.contains(ADMIN)) {
                //如果是管理员登录
                model.addAttribute("datas", true);
                List<Job> jobList = jobService.selectXNewJob(5);
                modelAndView.addObject("jobList", jobList);

                List<User> userList = userService.selectList(null);
                modelAndView.addObject("userList", userList);

                session.setAttribute("flag", "admin");
                //查询待审核的工作
                List<Job> jobWaiting = jobService.selectList(new QueryWrapper<Job>().eq("current_status", 0));
                modelAndView.addObject("jobWaiting", jobWaiting);

                modelAndView.setViewName("admin/index-admin");
            }

            //用户信息存储到model，便于从页面上获取数据
            session.setAttribute("account", account);
            session.setAttribute("loginUser", user);
            model.addAttribute("loginUser", user);
            return modelAndView;

        } catch (ServerOccurredException e) {
            logger.error("服务器异常");
            throw e;
        } catch (JsonMappingException e) {
            logger.error("天气映射异常：" + e.getMessage());
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            logger.error("解析天气时异常：" + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException();
        }
    }

    /**
     * 忘记密码
     * @return 忘记密码页面
     */
    @RequestMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    /**
     * 查看已经发布的工作（被管理员审核通过的工作）
     * 我们约定，审核状态：
     *      1：审核通过      0：待审核		默认值
     *      -1：审核拒绝     -2：招收完成
     *      2：取消申请
     * @return
     */
    @RequestMapping("/user/work-list")
    public ModelAndView workList() {
        ModelAndView modelAndView = new ModelAndView("user/work-list");
        List<Job> jobList = jobService.selectList(new QueryWrapper<Job>().eq("current_status", 1));
//        jobList.sort(new Comparator<Job>() {
//            @Override
//            public int compare(Job o1, Job o2) {
//                return o2.getReleaseTime().compareTo(o1.getReleaseTime());
//            }
//        });
        if (jobList.size() > 0) {
            modelAndView.addObject("jobList", jobList);
        }

        return modelAndView;
    }

    /**
     * 查看工作
     * @param request
     * @return
     */
    @RequestMapping("/view/view-details")
    public ModelAndView viewDetails(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("user/view-details");

        Integer jobId = Integer.valueOf(request.getParameter("jobId"));
        //级联查询，查询出发布该工作的账号
        Job job = jobService.selectJobWithCascadeById(jobId);
        modelAndView.addObject("job", job);

        //查询营业证书
        List<TradingCertificate> certificateList = tradingCertificateService.selectTradingCertificateListByJobId(jobId);
        modelAndView.addObject("certificateList", certificateList);

        //查询该工作的评论
        List<Comment> comments = commentService.selectCommentByJobId(jobId);
        modelAndView.addObject("comments", comments);

        //查询该工作的评分
        Map<String, Integer> scoreWithCount = commonFunction.getAvgScoreByJobId(jobId);
        modelAndView.addObject("avgScore", scoreWithCount.get("avgScore"));
        modelAndView.addObject("countWithScore", scoreWithCount.get("countWithScore"));

        return modelAndView;
    }

}

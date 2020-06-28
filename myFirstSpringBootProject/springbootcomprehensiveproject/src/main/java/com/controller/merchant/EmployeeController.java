package com.controller.merchant;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.authentication.User;
import com.entity.merchant.TradingCertificate;
import com.entity.payment.BankCard;
import com.entity.student.Resume;
import com.service.authentication.UserService;
import com.service.merchant.TradingCertificateService;
import com.service.payment.BankCardService;
import com.service.student.ResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 冉堃赤
 * @date 2020/3/30 9:57
 */
@Controller
public class EmployeeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private BankCardService bankCardService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${file-server-location}")
    private String fileServerLocation;

    @Autowired
    private TradingCertificateService tradingCertificateService;

    /**
     * 查看员工详情
     * @return
     */
    @PreAuthorize("hasAnyAuthority('I_MERCHANT','O_MERCHANT')")
    @RequestMapping("/employee/details")
    public ModelAndView employeeDetails(@RequestParam("employeeId") String userId, @RequestParam("jobId") String jobId
            , @RequestParam("resumeId") String resumeId, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("merchant/employee-details");
        Resume resume = resumeService.selectResumeById(Integer.valueOf(resumeId));
        BankCard bank = bankCardService.selectOne(new QueryWrapper<BankCard>().eq("u_id", userId));
        User user = (User) request.getSession().getAttribute("loginUser");
        BankCard myCard = bankCardService.selectOne(new QueryWrapper<BankCard>().eq("u_id", user.getId()));

        modelAndView.addObject("resume", resume);
        modelAndView.addObject("bankCard", bank);
        modelAndView.addObject("myCard", myCard);
        return modelAndView;
    }

    /**
     * 转账处理，具体逻辑在service层
     * @param from
     * @param to
     * @param salary
     * @return
     */
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('I_MERCHANT','O_MERCHANT')")
    @RequestMapping(value = "/merchant/transfer", method = RequestMethod.POST)
    public Object transferCounts(@RequestParam("from") String from, @RequestParam("to") String to
            , @RequestParam("salary") String salary) {
        Map<Object, Object> result = new HashMap<>(10);
        int influence = 0;
        try {
            influence = bankCardService.transferAccounts(from, to, Integer.valueOf(salary));
            if (influence > 0) {
                result.put("status", 1);
            }
        } catch (Exception e) {
            logger.error("转账出现异常：" + e.getMessage());
            result.put("status", 0);
        }
        return result;
    }

    @PreAuthorize("hasAnyAuthority('I_MERCHANT','O_MERCHANT')")
    @RequestMapping("/merchant/private-info")
    public ModelAndView tradingCertificate(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("merchant/private-info");
        User user = (User) session.getAttribute("loginUser");
        //获取证书列表
        List<TradingCertificate> certificateList = tradingCertificateService.selectTradingCertificateListByUserId(user.getId());
        modelAndView.addObject("certificateList", certificateList);
        return modelAndView;
    }

    /**
     * 证书验证
     * @param file
     * @param userId
     * @param session
     * @return
     */
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('I_MERCHANT','O_MERCHANT')")
    @RequestMapping(value = "/uploadTradingCertificate", method = RequestMethod.POST)
    public Object uploadTradingCertificate(@RequestParam("file")MultipartFile file, @RequestParam("u_id") String userId
            , HttpSession session) {
        if (file.isEmpty()) {
            return false;
        }
        if (tradingCertificateService.registeredCertificate(file, userId) > 0) {
            return true;
        } else {
            return false;
        }
    }
}

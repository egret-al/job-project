package com.merchant;

import com.SpringbootApplication;
import com.entity.merchant.Job;
import com.entity.merchant.TradingCertificate;
import com.service.merchant.JobService;
import com.service.merchant.TradingCertificateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/23 21:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestMerchant {

    @Autowired
    private JobService jobService;

    @Autowired
    private TradingCertificateService tradingCertificateService;

    @Test
    public void testSelectListByUid() throws Exception {
        List<TradingCertificate> certificateList = tradingCertificateService.selectTradingCertificateListByUserId(2);
        certificateList.forEach(System.out::println);
    }

    @Test
    public void testSelectFiveJob() throws Exception {
        List<Job> jobs = jobService.selectXNewJob(2);
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

    @Test
    public void testSelectAllJob() throws Exception {
        List<Job> jobs = jobService.selectAllJob();
        jobs.forEach(System.out::println);
    }

    @Test
    public void testSelectJobByUsername() throws Exception {
        List<Job> jobs = jobService.selectJobByUsername("O123456789");
        jobs.forEach(System.out::println);
    }
}

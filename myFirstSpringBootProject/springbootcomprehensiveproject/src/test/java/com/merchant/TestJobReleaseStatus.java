package com.merchant;

import com.SpringbootApplication;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.merchant.Job;
import com.entity.merchant.JobReleaseStatus;
import com.service.merchant.JobReleaseStatusService;
import com.service.merchant.JobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/24 15:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestJobReleaseStatus {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobReleaseStatusService jobReleaseStatusService;

    @Test
    public void testSelectJobByWaiting() throws Exception {
        List<Job> jobWaiting = jobService.selectList(new QueryWrapper<Job>().eq("current_status", 0));
        jobWaiting.forEach(System.out::println);
    }

    @Test
    public void testSelectJobStatus() throws Exception {
        List<JobReleaseStatus> list = jobReleaseStatusService.selectList(
                new QueryWrapper<JobReleaseStatus>().eq("job_id", 2));
        list.forEach(System.out::println);
    }

    @Test
    public void testJobSelectById() throws Exception {
        Job job = jobService.selectJobById(2);
        System.out.println(job);
    }
}

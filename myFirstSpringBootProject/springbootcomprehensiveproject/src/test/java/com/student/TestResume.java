package com.student;

import com.SpringbootApplication;
import com.entity.student.Resume;
import com.service.student.ResumeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/26 14:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestResume {

    @Autowired
    private ResumeService resumeService;

    @Test
    public void testAllPassedResume() throws Exception {
        List<Resume> resumeList = resumeService.selectAllPassedResumeByUsername("O123456789");
        for (Resume resume : resumeList) {
            System.out.println(resume);
        }
    }

    @Test
    public void testResumeSelect() throws Exception {
        Resume resume = resumeService.selectResumeById(1);
        System.out.println(resume);
    }
}

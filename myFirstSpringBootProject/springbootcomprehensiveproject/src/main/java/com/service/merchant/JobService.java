package com.service.merchant;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.entity.authentication.Account;
import com.entity.merchant.Job;
import com.entity.student.Resume;
import com.service.base.BaseMapperService;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 冉堃赤
 * @date 2020/3/23 10:58
 */
public interface JobService extends BaseMapperService<Job> {

    Job selectJobWithCascadeById(Integer id);

    List<Job> selectXNewJob(Integer x);

    List<Job> selectJobPassedByUsername(String username);

    Job selectJobById(Integer id);

    List<Job> selectJobByUsername(String username);

    int addReleaseJob(Job job, String username);

    List<Job> selectAllJob();
}

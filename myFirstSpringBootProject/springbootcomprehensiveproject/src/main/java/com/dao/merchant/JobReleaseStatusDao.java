package com.dao.merchant;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.merchant.JobReleaseStatus;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 冉堃赤
 * @date 2020/3/24 14:16
 */
@Mapper
@Repository("jobReleaseStatusDao")
public interface JobReleaseStatusDao extends BaseMapper<JobReleaseStatus> {


}

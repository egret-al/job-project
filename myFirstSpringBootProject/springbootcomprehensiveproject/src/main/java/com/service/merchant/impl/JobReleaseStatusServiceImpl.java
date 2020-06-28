package com.service.merchant.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.merchant.JobReleaseStatusDao;
import com.entity.merchant.JobReleaseStatus;
import com.service.merchant.JobReleaseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 冉堃赤
 * @date 2020/3/24 14:18
 */
@Service("jobReleaseStatus")
public class JobReleaseStatusServiceImpl implements JobReleaseStatusService {

    @Autowired
    private JobReleaseStatusDao jobReleaseStatusDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(JobReleaseStatus entity) {
        return jobReleaseStatusDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return jobReleaseStatusDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return jobReleaseStatusDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<JobReleaseStatus> wrapper) {
        return jobReleaseStatusDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return jobReleaseStatusDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(JobReleaseStatus entity) {
        return jobReleaseStatusDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(JobReleaseStatus entity, Wrapper<JobReleaseStatus> updateWrapper) {
        return jobReleaseStatusDao.update(entity, updateWrapper);
    }

    @Override
    public JobReleaseStatus selectById(Serializable id) {
        return jobReleaseStatusDao.selectById(id);
    }

    @Override
    public List<JobReleaseStatus> selectBatchIds(Collection<? extends Serializable> idList) {
        return jobReleaseStatusDao.selectBatchIds(idList);
    }

    @Override
    public List<JobReleaseStatus> selectByMap(Map<String, Object> columnMap) {
        return jobReleaseStatusDao.selectByMap(columnMap);
    }

    @Override
    public JobReleaseStatus selectOne(Wrapper<JobReleaseStatus> queryWrapper) {
        return jobReleaseStatusDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<JobReleaseStatus> queryWrapper) {
        return jobReleaseStatusDao.selectCount(queryWrapper);
    }

    @Override
    public List<JobReleaseStatus> selectList(Wrapper<JobReleaseStatus> queryWrapper) {
        return jobReleaseStatusDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<JobReleaseStatus> queryWrapper) {
        return jobReleaseStatusDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<JobReleaseStatus> queryWrapper) {
        return jobReleaseStatusDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<JobReleaseStatus> selectPage(IPage<JobReleaseStatus> page, Wrapper<JobReleaseStatus> queryWrapper) {
        return jobReleaseStatusDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<JobReleaseStatus> page, Wrapper<JobReleaseStatus> queryWrapper) {
        return jobReleaseStatusDao.selectMapsPage(page, queryWrapper);
    }
}

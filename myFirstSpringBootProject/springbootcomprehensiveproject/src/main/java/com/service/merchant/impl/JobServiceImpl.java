package com.service.merchant.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.merchant.JobDao;
import com.entity.merchant.Job;
import com.service.merchant.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工作操作的业务层
 * @author 冉堃赤
 * @date 2020/3/23 11:01
 */
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Override
    public Job selectJobWithCascadeById(Integer id) {
        return jobDao.selectJobWithCascadeById(id);
    }

    @Override
    public List<Job> selectXNewJob(Integer x) {
        return jobDao.selectXNewJob(x);
    }

    @Override
    public List<Job> selectJobPassedByUsername(String username) {
        return jobDao.selectJobPassedByUsername(username);
    }

    @Override
    public Job selectJobById(Integer id) {
        return jobDao.selectJobById(id);
    }

    @Override
    public List<Job> selectJobByUsername(String username) {
        return jobDao.selectJobByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addReleaseJob(Job job, String username) {
        return jobDao.addReleaseJob(job, username);
    }

    @Override
    public List<Job> selectAllJob() {
        return jobDao.selectAllJob();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Job entity) {
        return jobDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return jobDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return jobDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<Job> wrapper) {
        return jobDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return jobDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(Job entity) {
        return jobDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Job entity, Wrapper<Job> updateWrapper) {
        return jobDao.update(entity, updateWrapper);
    }

    @Override
    public Job selectById(Serializable id) {
        return jobDao.selectById(id);
    }

    @Override
    public List<Job> selectBatchIds(Collection<? extends Serializable> idList) {
        return jobDao.selectBatchIds(idList);
    }

    @Override
    public List<Job> selectByMap(Map<String, Object> columnMap) {
        return jobDao.selectByMap(columnMap);
    }

    @Override
    public Job selectOne(Wrapper<Job> queryWrapper) {
        return jobDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<Job> queryWrapper) {
        return jobDao.selectCount(queryWrapper);
    }

    @Override
    public List<Job> selectList(Wrapper<Job> queryWrapper) {
        return jobDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<Job> queryWrapper) {
        return jobDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<Job> queryWrapper) {
        return jobDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<Job> selectPage(IPage<Job> page, Wrapper<Job> queryWrapper) {
        return jobDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<Job> page, Wrapper<Job> queryWrapper) {
        return jobDao.selectMapsPage(page, queryWrapper);
    }
}

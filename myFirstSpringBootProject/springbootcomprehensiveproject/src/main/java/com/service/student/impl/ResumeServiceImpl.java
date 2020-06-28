package com.service.student.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.merchant.JobDao;
import com.dao.student.ResumeDao;
import com.entity.merchant.Job;
import com.entity.student.Resume;
import com.service.student.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 冉堃赤
 * @date 2020/3/26 14:46
 */
@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Autowired
    private JobDao jobDao;

    /**
     * 根据用户名，查询该公司所有的合格的简历
     * @param username
     * @return
     */
    @Override
    public List<Resume> selectAllPassedResumeByUsername(String username) {
        //查询商家发布通过的所有工作
        List<Job> jobs = jobDao.selectJobPassedByUsername(username);
        List<Integer> jobIdList = new ArrayList<Integer>(10);
        //将所有被通过的job的id添加到list中，便于mybatis plus的集合查询
        for (Job job : jobs) {
            jobIdList.add(job.getId());
        }
        List<Resume> resumeList = new ArrayList<Resume>(10);

        //遍历查询所有jobId
        for (Integer integer : jobIdList) {
            List<Resume> tempList = resumeDao.selectResumeByJid(integer);
            //查询结果全部添加到resumeList
            resumeList.addAll(tempList);
        }

        return resumeList;
    }

    @Override
    public List<Resume> selectResumeBySid(Integer sId) {
        return resumeDao.selectResumeBySid(sId);
    }

    @Override
    public List<Resume> selectWaitCheckResumeByJid(Integer jId) {
        return resumeDao.selectWaitCheckResumeByJid(jId);
    }

    /**
     * 根据id查询job，判断该job是否招收完成，如果完成，则同意招收失败，否则，让商家做决定
     * 规定：
     *      1：审核通过
     *      0：待审核		默认值
     *      -1：审核拒绝
     *      -2：招收完成
     *      2：取消申请
     * @param jobId 工作的id
     * @return 是否招收完成
     */
    @Override
    public boolean isFull(Integer jobId) {
        Job job = jobDao.selectJobById(jobId);
        return job.getCurrentStatus() == -2;
    }

    /**
     * 拒绝岗位申请
     * @param resumeId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int rejectStudentResume(String resumeId) {
        Resume resume = resumeDao.selectOne(new QueryWrapper<Resume>().eq("id", resumeId));
        //拒绝申请
        resume.setCheckStatus(-1);
        int resumeInfluenceRow = resumeDao.updateById(resume);
        return resumeInfluenceRow;
    }

    /**
     * 首先查询该岗位是否招收已经完成，如果已经完成，那么提示完成，否则同意
     * 同意学生的工作申请
     * 简历状态，0为待审核，1为通过，-1为拒绝
     * @param resumeId
     * @param jobId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int agreeStudentResume(Integer resumeId, Integer jobId) {
        if (isFull(jobId)) {
            //招收已满
            return -2;
        }
        Resume resume = resumeDao.selectOne(new QueryWrapper<Resume>().eq("id", resumeId));
        resume.setCheckStatus(1);
        //修改简历审核状态为通过
        int resumeInfluenceRow = resumeDao.updateById(resume);

        //先查询工作岗位
        Job job = jobDao.selectOne(new QueryWrapper<Job>().eq("id", jobId));
        job.setRecruitCount(job.getRecruitCount() - 1);
        int jobInfluenceRow = jobDao.updateById(job);

        return resumeInfluenceRow + jobInfluenceRow;
    }

    @Override
    public List<Resume> selectResumeByJid(Integer jId) {
        return resumeDao.selectResumeByJid(jId);
    }

    @Override
    public Resume selectResumeById(Integer id) {
        return resumeDao.selectResumeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Resume entity) {
        return resumeDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return resumeDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return resumeDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<Resume> wrapper) {
        return resumeDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return resumeDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(Resume entity) {
        return resumeDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Resume entity, Wrapper<Resume> updateWrapper) {
        return resumeDao.update(entity, updateWrapper);
    }

    @Override
    public Resume selectById(Serializable id) {
        return resumeDao.selectById(id);
    }

    @Override
    public List<Resume> selectBatchIds(Collection<? extends Serializable> idList) {
        return resumeDao.selectBatchIds(idList);
    }

    @Override
    public List<Resume> selectByMap(Map<String, Object> columnMap) {
        return resumeDao.selectByMap(columnMap);
    }

    @Override
    public Resume selectOne(Wrapper<Resume> queryWrapper) {
        return selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<Resume> queryWrapper) {
        return resumeDao.selectCount(queryWrapper);
    }

    @Override
    public List<Resume> selectList(Wrapper<Resume> queryWrapper) {
        return resumeDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<Resume> queryWrapper) {
        return resumeDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<Resume> queryWrapper) {
        return resumeDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<Resume> selectPage(IPage<Resume> page, Wrapper<Resume> queryWrapper) {
        return resumeDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<Resume> page, Wrapper<Resume> queryWrapper) {
        return resumeDao.selectMapsPage(page, queryWrapper);
    }
}

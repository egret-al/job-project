package com.service.other.impl;

import com.dao.other.CreditGradeDao;
import com.service.other.CreditGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/15 8:48
 */
@Service("creditGradeService")
public class CreditGradeServiceImpl implements CreditGradeService {

    @Autowired
    private CreditGradeDao creditGradeDao;


    /**
     * 首先查询该学生是否对该工作评分过，如果评分过，则给出标志提示已经评分，否则，评分成功
     * @param jobId 商家id
     * @param studentId 学生id
     * @param score 评分
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertCreditGrade(Integer jobId, Integer studentId, Integer score) {
        return creditGradeDao.getCountByMerchantIdAndStudentId(jobId, studentId) > 0 ? 0 :
                creditGradeDao.insertCreditGrade(jobId, studentId, score);
    }

    /**
     * 根据工作id获取评分
     * @param jobId 工作id
     * @return 评分
     */
    @Override
    public Integer getAverageScoreByJobId(Integer jobId) {
        Integer score = creditGradeDao.getAverageScoreByJobId(jobId);
        if (score == null) {
            return 0;
        }
        return score;
    }

    @Override
    public int getCountByMerchantIdAndStudentId(Integer jobId, Integer studentId) {
        return creditGradeDao.getCountByMerchantIdAndStudentId(jobId, studentId);
    }

    /**
     * 根据工作id查询评分人数
     * @param jobId 工作id
     * @return 评分人数
     */
    @Override
    public int getCountByJobId(Integer jobId) {
        return creditGradeDao.getCountByJobId(jobId);
    }
}

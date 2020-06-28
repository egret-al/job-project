package com.service.other;


/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/15 8:47
 */
public interface CreditGradeService {

    /**
     * 学生对该工作的评分
     * @param jobId 工作id
     * @param studentId 学生id
     * @param score 分数
     * @return 影响行数
     */
    int insertCreditGrade(Integer jobId, Integer studentId, Integer score);

    /**
     * 根据工作id获取评分
     * @param jobId 商家id
     * @return 评分
     */
    Integer getAverageScoreByJobId(Integer jobId);

    /**
     * 提供给业务层用于判断该用户是否评分过
     * @param jobId 工作id
     * @param studentId 学生id
     * @return 是否评分过
     */
    int getCountByMerchantIdAndStudentId(Integer jobId, Integer studentId);

    /**
     * 根据工作id查询评分人数
     * @param jobId 工作id
     * @return 评分人数
     */
    int getCountByJobId(Integer jobId);
}

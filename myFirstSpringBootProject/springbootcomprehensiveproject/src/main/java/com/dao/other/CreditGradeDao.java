package com.dao.other;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/15 8:38
 */
@Mapper
@Repository("creditGrade")
public interface CreditGradeDao {

    /**
     * 学生对该工作的评分
     * @param jobId 工作id
     * @param studentId 学生id
     * @param score 分数
     * @return 影响行数
     */
    @Insert("INSERT INTO t_credit_grade(job_id, student_id, grade_score) VALUES(#{jobId}, #{studentId}, #{score})")
    int insertCreditGrade(@Param("jobId") Integer jobId, @Param("studentId") Integer studentId, @Param("score") Integer score);

    /**
     * 根据工作id获取评分
     * @param jobId 商家id
     * @return 评分
     */
    @Select("SELECT AVG(grade_score) FROM t_credit_grade WHERE job_id = #{jobId}")
    Integer getAverageScoreByJobId(@Param("jobId") Integer jobId);

    /**
     * 提供给业务层用于判断该用户是否评分过
     * @param jobId 工作id
     * @param studentId 学生id
     * @return 是否评分过
     */
    @Select("SELECT COUNT(*) FROM t_credit_grade WHERE job_id = #{jobId} AND student_id = #{studentId}")
    int getCountByMerchantIdAndStudentId(@Param("jobId") Integer jobId, @Param("studentId") Integer studentId);

    /**
     * 根据工作id查询评分人数
     * @param jobId 工作id
     * @return 评分人数
     */
    @Select("SELECT COUNT(*) FROM t_credit_grade WHERE job_id = #{jobId}")
    int getCountByJobId(@Param("jobId") Integer jobId);
}

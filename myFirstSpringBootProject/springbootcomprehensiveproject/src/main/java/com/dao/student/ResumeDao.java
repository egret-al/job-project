package com.dao.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.student.Resume;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/26 12:10
 */
@Mapper
@Repository("resumeDao")
public interface ResumeDao extends BaseMapper<Resume> {

    /**
     * 根据简历id查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM t_resume WHERE id=#{id}")
    @Results(id = "resumeMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "s_id", property = "studentId"),
            @Result(column = "j_id", property = "jobId"),
            @Result(column = "file_path", property = "filePath"),
            @Result(column = "apply_time", property = "applyTime"),
            @Result(column = "check_status", property = "checkStatus"),
            @Result(column = "s_id", property = "student", one = @One(
                    select = "com.dao.authentication.UserDao.selectUserById", fetchType = FetchType.EAGER
            )),
            @Result(column = "j_id", property = "job", one = @One(
                    select = "com.dao.merchant.JobDao.selectJobById", fetchType = FetchType.EAGER
            ))
    })
    Resume selectResumeById(Integer id);

    /**
     * 根据工作id查询所有简历
     * @param jId 工作id
     * @return
     */
    @ResultMap("resumeMapper")
    @Select("SELECT * FROM t_resume WHERE j_id=#{jId}")
    List<Resume> selectResumeByJid(Integer jId);

    /**
     * 查询待审核的简历
     * @param jId
     * @return
     */
    @ResultMap("resumeMapper")
    @Select("SELECT * FROM t_resume WHERE (j_id=#{jId} AND check_status = 0)")
    List<Resume> selectWaitCheckResumeByJid(Integer jId);

    /**
     * 根据学生id查询简历
     * @param sId
     * @return
     */
    @ResultMap("resumeMapper")
    @Select("SELECT * FROM t_resume WHERE s_id=#{sId}")
    List<Resume> selectResumeBySid(Integer sId);
}

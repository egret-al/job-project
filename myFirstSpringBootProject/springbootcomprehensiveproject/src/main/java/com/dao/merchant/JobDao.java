package com.dao.merchant;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.authentication.Account;
import com.entity.merchant.Job;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/23 10:43
 */
@Mapper
@Repository("jobDao")
public interface JobDao extends BaseMapper<Job> {

    /**
     * 商家发布工作
     * @param job 工作内容对象
     * @param username 登录账户
     * @return 影响行数
     */
    @Options(useGeneratedKeys = true, keyProperty = "job.id", keyColumn = "id")
    @Insert("INSERT INTO t_job(job_theme,job_tempt,job_address,promulgator,salary,recruit_count,job_description," +
            "job_requirement,info_supplement,release_time,username,working_time) VALUES(#{job.jobTheme},#{job.jobTempt}," +
            "#{job.jobAddress},#{job.promulgator},#{job.salary},#{job.recruitCount},#{job.jobDescription}," +
            "#{job.jobRequirement},#{job.infoSupplement},#{job.releaseTime},#{accountNum},#{job.workingTime})")
    int addReleaseJob(@Param("job") Job job, @Param("accountNum") String username);

    /**
     * 查询所有的岗位，包括待审核
     * @return
     */
    @Select("SELECT * FROM t_job")
    @Results(id = "jobMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "job_theme", property = "jobTheme"),
            @Result(column = "job_tempt", property = "jobTempt"),
            @Result(column = "job_address", property = "jobAddress"),
            @Result(column = "promulgator", property = "promulgator"),
            @Result(column = "salary", property = "salary"),
            @Result(column = "recruit_count", property = "recruitCount"),
            @Result(column = "job_description", property = "jobDescription"),
            @Result(column = "job_requirement", property = "jobRequirement"),
            @Result(column = "info_supplement", property = "infoSupplement"),
            @Result(column = "current_status", property = "currentStatus"),
            @Result(column = "username", property = "username"),
            @Result(column = "username", property = "account", one = @One(
                    select = "com.dao.authentication.AccountDao.selectUserByUsername", fetchType = FetchType.EAGER
            )),
            @Result(column = "release_time", property = "releaseTime"),
            @Result(column = "working_time", property = "workingTime")
    })
    List<Job> selectAllJob();

    /**
     * 根据发布者查询发布的所有岗位
     * @return
     */
    @ResultMap("jobMapper")
    @Select("SELECT * FROM t_job WHERE username=#{username}")
    List<Job> selectJobByUsername(@Param("username") String username);

    /**
     * 根据登录账号查询所有被批准通过的工作
     * @param username
     * @return
     */
    @ResultMap("jobMapper")
    @Select("SELECT * FROM t_job WHERE username=#{username} AND (current_status=1 OR current_status=-2)")
    List<Job> selectJobPassedByUsername(@Param("username") String username);

    /**
     * 根据id查询工作
     * @param id
     * @return
     */
    @ResultMap("jobMapper")
    @Select("SELECT * FROM t_job WHERE id=#{id}")
    Job selectJobById(Integer id);

    /**
     * 查询最新的x条数据，无级联映射
     * @return
     */
    @Select("SELECT * FROM t_job ORDER BY release_time DESC LIMIT #{x}")
    List<Job> selectXNewJob(Integer x);

    /**
     * 级联查询工作id，映射出account
     * @param id jobId
     * @return 级联映射的job对象
     */
    @ResultMap("jobMapper")
    @Select("SELECT * FROM t_job WHERE id=#{id}")
    Job selectJobWithCascadeById(@Param("id") Integer id);
}

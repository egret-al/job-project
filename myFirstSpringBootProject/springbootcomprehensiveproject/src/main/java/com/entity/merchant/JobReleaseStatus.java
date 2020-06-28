package com.entity.merchant;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 工作发布状态
 * @author 冉堃赤
 * @date 2020/3/24 14:13
 */
@TableName("t_job_status")
public class JobReleaseStatus {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("job_id")
    private Integer jobId;

    @TableField("is_pass")
    private Integer isPass;

    @TableField("handle_time")
    private Date handleTime;

    @TableField("explain_content")
    private String explainContent;

    @TableField(exist = false)
    private Job job;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getExplainContent() {
        return explainContent;
    }

    public void setExplainContent(String explainContent) {
        this.explainContent = explainContent;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "JobReleaseStatus{" +
                "id=" + id +
                ", jobId=" + jobId +
                ", isPass=" + isPass +
                ", handleTime=" + handleTime +
                ", explainContent='" + explainContent + '\'' +
                ", job=" + job +
                '}';
    }
}

package com.entity.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.entity.authentication.User;
import com.entity.merchant.Job;

import java.util.Date;

/**
 * @author 冉堃赤
 * @date 2020/3/26 12:04
 */
@TableName("t_resume")
public class Resume {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("s_id")
    private Integer studentId;

    @TableField("j_id")
    private Integer jobId;

    @TableField("file_path")
    private String filePath;

    @TableField("apply_time")
    private Date applyTime;

    @TableField("check_status")
    private Integer checkStatus;

    @TableField(exist = false)
    private User student;

    @TableField(exist = false)
    private Job job;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", jobId=" + jobId +
                ", filePath='" + filePath + '\'' +
                ", applyTime=" + applyTime +
                ", checkStatus=" + checkStatus +
                ", student=" + student +
                ", job=" + job +
                '}';
    }
}

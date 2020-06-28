package com.entity.other;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.entity.authentication.User;
import com.entity.merchant.Job;

import java.util.Date;

/**
 * 评论的实体类
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/12 19:29
 */
@TableName("t_comment")
public class Comment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("message")
    private String message;

    @TableField("releases_time")
    private Date releasesTime;

    @TableField("u_id")
    private Integer userId;

    @TableField("j_id")
    private Integer jobId;

    @TableField(exist = false)
    private Job job;

    @TableField(exist = false)
    private User user;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getReleasesTime() {
        return releasesTime;
    }

    public void setReleasesTime(Date releasesTime) {
        this.releasesTime = releasesTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", releasesTime=" + releasesTime +
                ", userId=" + userId +
                ", jobId=" + jobId +
                ", job=" + job +
                ", user=" + user +
                '}';
    }
}

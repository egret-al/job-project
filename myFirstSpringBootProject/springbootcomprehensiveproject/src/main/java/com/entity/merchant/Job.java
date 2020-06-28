package com.entity.merchant;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.entity.authentication.Account;
import com.entity.student.Resume;

import java.util.Date;
import java.util.List;

/**
 * 工作的实体类
 * @author 冉堃赤
 * @date 2020/3/23 10:34
 */

@TableName("t_job")
public class Job {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("job_theme")
    private String jobTheme;

    @TableField("job_tempt")
    private String jobTempt;

    @TableField("job_address")
    private String jobAddress;

    private String promulgator;
    private Integer salary;

    @TableField("recruit_count")
    private Integer recruitCount;

    @TableField("job_description")
    private String jobDescription;

    @TableField("job_requirement")
    private String jobRequirement;

    @TableField("info_supplement")
    private String infoSupplement;

    @TableField("current_status")
    private Integer currentStatus;

    @TableField(exist = false)
    private Account account;

    @TableField("release_time")
    private Date releaseTime;

    private String username;

    @TableField("working_time")
    private String workingTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTheme() {
        return jobTheme;
    }

    public void setJobTheme(String jobTheme) {
        this.jobTheme = jobTheme;
    }

    public String getJobTempt() {
        return jobTempt;
    }

    public void setJobTempt(String jobTempt) {
        this.jobTempt = jobTempt;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public String getPromulgator() {
        return promulgator;
    }

    public void setPromulgator(String promulgator) {
        this.promulgator = promulgator;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getRecruitCount() {
        return recruitCount;
    }

    public void setRecruitCount(Integer recruitCount) {
        this.recruitCount = recruitCount;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobRequirement() {
        return jobRequirement;
    }

    public void setJobRequirement(String jobRequirement) {
        this.jobRequirement = jobRequirement;
    }

    public String getInfoSupplement() {
        return infoSupplement;
    }

    public void setInfoSupplement(String infoSupplement) {
        this.infoSupplement = infoSupplement;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobTheme='" + jobTheme + '\'' +
                ", jobTempt='" + jobTempt + '\'' +
                ", jobAddress='" + jobAddress + '\'' +
                ", promulgator='" + promulgator + '\'' +
                ", salary=" + salary +
                ", recruitCount=" + recruitCount +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobRequirement='" + jobRequirement + '\'' +
                ", infoSupplement='" + infoSupplement + '\'' +
                ", currentStatus=" + currentStatus +
                ", account=" + account +
                ", releaseTime=" + releaseTime +
                ", username='" + username + '\'' +
                ", workingTime='" + workingTime + '\'' +
                '}';
    }
}

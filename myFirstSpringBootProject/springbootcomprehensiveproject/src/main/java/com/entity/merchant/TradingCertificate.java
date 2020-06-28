package com.entity.merchant;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.entity.authentication.User;

import java.util.Date;

/**
 * @author 冉堃赤
 * @date 2020/4/5 22:59
 */
@TableName("t_trading_certificate")
public class TradingCertificate {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("uid")
    private Integer userId;

    @TableField("file_path")
    private String filePath;

    @TableField("upload_time")
    private Date uploadTime;

    @TableField(exist = false)
    private User user;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "TradingCertificate{" +
                "id=" + id +
                ", userId=" + userId +
                ", filePath='" + filePath + '\'' +
                ", uploadTime=" + uploadTime +
                ", user=" + user +
                '}';
    }
}

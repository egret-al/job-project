package com.entity.payment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author 冉堃赤
 * @date 2020/3/31 8:53
 */
@TableName("t_transfer_record")
public class TransferRecord {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("card_from")
    private String cardFrom;

    @TableField("transfer_count")
    private Integer transferCount;

    @TableField("card_to")
    private String cardTo;

    @TableField("transfer_time")
    private Date transferTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(String cardFrom) {
        this.cardFrom = cardFrom;
    }

    public Integer getTransferCount() {
        return transferCount;
    }

    public void setTransferCount(Integer transferCount) {
        this.transferCount = transferCount;
    }

    public String getCardTo() {
        return cardTo;
    }

    public void setCardTo(String cardTo) {
        this.cardTo = cardTo;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public String toString() {
        return "TransferRecord{" +
                "id=" + id +
                ", cardFrom='" + cardFrom + '\'' +
                ", transferCount=" + transferCount +
                ", cardTo='" + cardTo + '\'' +
                ", transferTime=" + transferTime +
                '}';
    }
}

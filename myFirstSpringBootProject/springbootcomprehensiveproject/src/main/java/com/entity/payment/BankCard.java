package com.entity.payment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.entity.authentication.User;

/**
 * 银行卡实体类
 * @author 冉堃赤
 * @date 2020/3/29 10:33
 */
@TableName("t_bank_card")
public class BankCard {

    @TableId("card_number")
    private String cardNumber;

    @TableField("balance")
    private Integer balance;

    @TableField("u_id")
    private Integer userId;

    @TableField(exist = false)
    private User user;

    public BankCard() {
    }

    public BankCard(String cardNumber, Integer balance, Integer userId) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }
}

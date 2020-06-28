package com.entity.student;

import java.io.Serializable;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/4/24 10:01
 */
public class RegisterStudentBean implements Serializable {

    private String realName;
    private String idCard;
    private String sex;
    private String nation;
    private String studentNum;
    private String bindEmail;
    private String homeAddress;
    private String creditCard;
    private String phone;
    private String academy;
    private String password;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getBindEmail() {
        return bindEmail;
    }

    public void setBindEmail(String bindEmail) {
        this.bindEmail = bindEmail;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterStudentBean{" +
                "realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", bindEmail='" + bindEmail + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", phone='" + phone + '\'' +
                ", academy='" + academy + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

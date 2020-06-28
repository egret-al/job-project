package com.entity.authentication;

/**
 * @author 冉堃赤
 * @date 2020/3/22 10:55
 */
public class RegisterMerchant {

    private String number;
    private String password;
    private String realName;
    private String identification;
    private String sex;
    private String nation;
    private String bindEmail;
    private String currentAddress;
    private String organ;
    private String phone;
    private String bank;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
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

    public String getBindEmail() {
        return bindEmail;
    }

    public void setBindEmail(String bindEmail) {
        this.bindEmail = bindEmail;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "RegisterMerchant{" +
                "number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", identification='" + identification + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", bindEmail='" + bindEmail + '\'' +
                ", currentAddress='" + currentAddress + '\'' +
                ", organ='" + organ + '\'' +
                ", phone='" + phone + '\'' +
                ", bank='" + bank + '\'' +
                '}';
    }
}

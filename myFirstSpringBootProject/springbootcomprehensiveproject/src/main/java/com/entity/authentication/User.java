package com.entity.authentication;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.*;

/**
 * 用户实体类
 * @author 冉堃赤
 * @date 2020/3/14 14:00
 */
@TableName("t_user")
public class User implements Cloneable, Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String identification;
    private String gender;
    private String nation;

    @TableField("t_account")
    private String account;

    @TableField("bind_mail")
    private String email;

    @TableField("current_address")
    private String currentAddress;

    @TableField("subsidiary_organ")
    private String subsidiaryOrgan;
    private String phone;

    public User() { }

    public User(String name, String identification, String gender, String nation, String account,
                String email, String currentAddress, String subsidiaryOrgan, String phone) {
        this.name = name;
        this.identification = identification;
        this.gender = gender;
        this.nation = nation;
        this.account = account;
        this.email = email;
        this.currentAddress = currentAddress;
        this.subsidiaryOrgan = subsidiaryOrgan;
        this.phone = phone;
    }

    //-----------------------原型设计模式-------------------------

    /**
     * 浅克隆
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        return user;
    }

    /**
     * 深克隆
     * @return
     */
    public Object deepClone() {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getSubsidiaryOrgan() {
        return subsidiaryOrgan;
    }

    public void setSubsidiaryOrgan(String subsidiaryOrgan) {
        this.subsidiaryOrgan = subsidiaryOrgan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identification='" + identification + '\'' +
                ", gender='" + gender + '\'' +
                ", nation='" + nation + '\'' +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", currentAddress='" + currentAddress + '\'' +
                ", subsidiaryOrgan='" + subsidiaryOrgan + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

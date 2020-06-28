package com.entity.function;

/**
 * 重置密码的实体类
 * @author 冉堃赤
 * @date 2020/3/21 9:56
 */
public class ForgotPassword {

    private String account;
    private String newPassword;
    private String confirmPassword;
    private String bindEmail;
    private String verificationCode;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getBindEmail() {
        return bindEmail;
    }

    public void setBindEmail(String bindEmail) {
        this.bindEmail = bindEmail;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "ForgotPassword{" +
                "account='" + account + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", bindEmail='" + bindEmail + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}

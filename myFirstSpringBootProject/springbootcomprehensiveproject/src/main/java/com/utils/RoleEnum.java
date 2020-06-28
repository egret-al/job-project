package com.utils;

/**
 * @author 冉堃赤
 * @date 2020/4/3 10:08
 */
public enum RoleEnum {

    STUDENT("STUDENT"), ADMIN("ADMIN"), TEACHER("TEACHER"), I_MERCHANT("I_MERCHANT"), O_MERCHANT("O_MERCHANT");

    private String roleName;

    private RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

package com.entity.coronavirus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 省、直辖市、区对应的新型冠状病毒的实体类
 * @author 冉堃赤
 * @date 2020/3/19 9:35
 */
@TableName("t_province_coronavirus_data")
public class CoronavirusData {

    @TableId("id")
    private Integer id;

    @TableField("province_code")
    private Integer provinceCode;

    @TableField("update_date")
    private Date updateDate;

    @TableField("confirm")
    private Integer confirm;

    @TableField("death")
    private Integer death;

    @TableField("cure")
    private Integer cure;

    @TableField("illustrate")
    private String illustrate;

    @TableField(exist = false)
    private Province province;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }

    public Integer getCure() {
        return cure;
    }

    public void setCure(Integer cure) {
        this.cure = cure;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "CoronavirusData{" +
                "id=" + id +
                ", provinceCode=" + provinceCode +
                ", updateDate=" + updateDate +
                ", confirm=" + confirm +
                ", death=" + death +
                ", cure=" + cure +
                ", illustrate='" + illustrate + '\'' +
                ", province=" + province +
                '}';
    }
}

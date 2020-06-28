package com.entity.coronavirus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 省份、直辖市或区的实体类
 * @author 冉堃赤
 * @date 2020/3/19 9:32
 */
@TableName("t_province")
public class Province {

    @TableId("province_name")
    private String provinceName;

    @TableField("province_comment")
    private String provinceComment;

    @TableField("province_code")
    private Integer provinceCode;

    //对应的数据
    @TableField(exist = false)
    private List<CoronavirusData> coronavirusDataList;

    public List<CoronavirusData> getCoronavirusDataList() {
        return coronavirusDataList;
    }

    public void setCoronavirusDataList(List<CoronavirusData> coronavirusDataList) {
        this.coronavirusDataList = coronavirusDataList;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceComment() {
        return provinceComment;
    }

    public void setProvinceComment(String provinceComment) {
        this.provinceComment = provinceComment;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provinceName='" + provinceName + '\'' +
                ", provinceComment='" + provinceComment + '\'' +
                ", provinceCode=" + provinceCode +
                ", coronavirusDataList=" + coronavirusDataList +
                '}';
    }
}

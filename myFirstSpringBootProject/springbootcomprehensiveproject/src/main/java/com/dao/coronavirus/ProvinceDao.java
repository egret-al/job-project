package com.dao.coronavirus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.coronavirus.CoronavirusData;
import com.entity.coronavirus.Province;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 省份持久层
 * @author 冉堃赤
 * @date 2020/3/19 9:44
 */
@Mapper
@Repository("provinceDao")
public interface ProvinceDao extends BaseMapper<Province> {

    /**
     * 根据省份名称获取省份以及该省份的疫情数据对象（懒加载）
     * @param name 省份名称
     * @return 省份对象
     */
    @Select("select * from t_province where province_name=#{name}")
    @Results(id = "provinceMapper", value = {
            @Result(id = true, column = "province_name", property = "provinceName"),
            @Result(column = "province_comment", property = "provinceComment"),
            @Result(column = "province_code", property = "provinceCode"),
            @Result(column = "province_code", property = "coronavirusDataList", many = @Many(
                    select = "com.dao.coronavirus.CoronavirusDataDao.selectCoronavirusByProvinceCode",
                    fetchType = FetchType.LAZY
            ))
    })
    Province selectProvinceWithCoronavirusByProvinceName(String name);

    @Select("select * from t_province")
    @ResultMap("provinceMapper")
    List<Province> selectAllProvinceWithCoronavirus();

    /**
     * @ResultMap("provinceMapper")：避免深度递归，在此方法不能添该注解
     * @param code 城市代码
     * @return 数据对象
     */
    @Select("select * from t_province where province_code=#{code}")
    Province selectProvinceByCode(int code);
}

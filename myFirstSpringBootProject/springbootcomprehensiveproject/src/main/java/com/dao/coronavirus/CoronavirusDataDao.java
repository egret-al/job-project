package com.dao.coronavirus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.coronavirus.CoronavirusData;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 新型冠状病毒数据持久层
 * @author 冉堃赤
 * @date 2020/3/19 10:24
 */
@Mapper
@Repository("coronavirusDataDao")
public interface CoronavirusDataDao extends BaseMapper<CoronavirusData> {

    /**
     * 根据省份编码查找疫情数据
     * @param code 省份编码
     * @return 疫情数据List
     */
    @Select("select * from t_province_coronavirus_data where province_code=#{code}")
    @Results(id = "coronavirusMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "province_code", property = "provinceCode"),
            @Result(column = "update_date", property = "updateDate"),
            @Result(column = "confirm", property = "confirm"),
            @Result(column = "death", property = "death"),
            @Result(column = "cure", property = "cure"),
            @Result(column = "illustrate", property = "illustrate"),
            @Result(column = "province_code", property = "province", one = @One(
                    select = "com.dao.coronavirus.ProvinceDao.selectProvinceByCode",
                    fetchType = FetchType.EAGER
            ))
    })
    List<CoronavirusData> selectCoronavirusByProvinceCode(int code);

    @ResultMap("coronavirusMapper")
    @Select("SELECT id, province_code, update_date, confirm, death, cure, illustrate " +
            "FROM t_province_coronavirus_data a WHERE 1>(SELECT COUNT(*) FROM t_province_coronavirus_data " +
            "WHERE province_code=a.province_code AND update_date>a.update_date) " +
            "ORDER BY province_code, update_date DESC;")
    List<CoronavirusData> selectAllLastDayData();
}

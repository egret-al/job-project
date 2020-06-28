package com.service.coronavirus;

import com.entity.coronavirus.Province;
import com.service.base.BaseMapperService;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/19 9:46
 */
public interface ProvinceService extends BaseMapperService<Province> {

    List<Province> selectAllProvinceWithCoronavirus();

    Province selectProvinceWithCoronavirusByProvinceName(String name);
}

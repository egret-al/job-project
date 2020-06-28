package com.service.coronavirus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.entity.coronavirus.CoronavirusData;
import com.service.base.BaseMapperService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 冉堃赤
 * @date 2020/3/19 10:28
 */
public interface CoronavirusDataService extends BaseMapperService<CoronavirusData> {

    List<CoronavirusData> selectAllLastDayData();

    List<CoronavirusData> selectCoronavirusByProvinceCode(int code);
}

package com.service.coronavirus.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.coronavirus.CoronavirusDataDao;
import com.entity.coronavirus.CoronavirusData;
import com.service.coronavirus.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 新型冠状病毒数据业务层
 * @author 冉堃赤
 * @date 2020/3/19 10:32
 */
@Service("coronavirusDataService")
public class CoronavirusDataServiceImpl implements CoronavirusDataService {

    private CoronavirusDataDao coronavirusDataDao;

    @Autowired
    public void setCoronavirusDataDao(CoronavirusDataDao coronavirusDataDao) {
        this.coronavirusDataDao = coronavirusDataDao;
    }

    @Override
    public List<CoronavirusData> selectAllLastDayData() {
        return coronavirusDataDao.selectAllLastDayData();
    }

    @Override
    public List<CoronavirusData> selectCoronavirusByProvinceCode(int code) {
        return coronavirusDataDao.selectCoronavirusByProvinceCode(code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CoronavirusData entity) {
        return coronavirusDataDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return coronavirusDataDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return coronavirusDataDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<CoronavirusData> wrapper) {
        return coronavirusDataDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return coronavirusDataDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(CoronavirusData entity) {
        return coronavirusDataDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(CoronavirusData entity, Wrapper<CoronavirusData> updateWrapper) {
        return coronavirusDataDao.update(entity, updateWrapper);
    }

    @Override
    public CoronavirusData selectById(Serializable id) {
        return coronavirusDataDao.selectById(id);
    }

    @Override
    public List<CoronavirusData> selectBatchIds(Collection<? extends Serializable> idList) {
        return coronavirusDataDao.selectBatchIds(idList);
    }

    @Override
    public List<CoronavirusData> selectByMap(Map<String, Object> columnMap) {
        return coronavirusDataDao.selectByMap(columnMap);
    }

    @Override
    public CoronavirusData selectOne(Wrapper<CoronavirusData> queryWrapper) {
        return coronavirusDataDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<CoronavirusData> queryWrapper) {
        return coronavirusDataDao.selectCount(queryWrapper);
    }

    @Override
    public List<CoronavirusData> selectList(Wrapper<CoronavirusData> queryWrapper) {
        return coronavirusDataDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<CoronavirusData> queryWrapper) {
        return coronavirusDataDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<CoronavirusData> queryWrapper) {
        return coronavirusDataDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<CoronavirusData> selectPage(IPage<CoronavirusData> page, Wrapper<CoronavirusData> queryWrapper) {
        return coronavirusDataDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<CoronavirusData> page, Wrapper<CoronavirusData> queryWrapper) {
        return coronavirusDataDao.selectMapsPage(page, queryWrapper);
    }
}

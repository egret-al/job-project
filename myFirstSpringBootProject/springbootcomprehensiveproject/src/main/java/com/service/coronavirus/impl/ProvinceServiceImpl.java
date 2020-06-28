package com.service.coronavirus.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.coronavirus.ProvinceDao;
import com.entity.coronavirus.Province;
import com.service.coronavirus.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 省份业务层
 * @author 冉堃赤
 * @date 2020/3/19 9:49
 */
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    private ProvinceDao provinceDao;

    @Autowired
    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }

    @Override
    public List<Province> selectAllProvinceWithCoronavirus() {
        return provinceDao.selectAllProvinceWithCoronavirus();
    }

    @Override
    public Province selectProvinceWithCoronavirusByProvinceName(String name) {
        return provinceDao.selectProvinceWithCoronavirusByProvinceName(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Province entity) {
        return provinceDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return provinceDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return provinceDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<Province> wrapper) {
        return provinceDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return provinceDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(Province entity) {
        return provinceDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Province entity, Wrapper<Province> updateWrapper) {
        return provinceDao.update(entity, updateWrapper);
    }

    @Override
    public Province selectById(Serializable id) {
        return provinceDao.selectById(id);
    }

    @Override
    public List<Province> selectBatchIds(Collection<? extends Serializable> idList) {
        return provinceDao.selectBatchIds(idList);
    }

    @Override
    public List<Province> selectByMap(Map<String, Object> columnMap) {
        return provinceDao.selectByMap(columnMap);
    }

    @Override
    public Province selectOne(Wrapper<Province> queryWrapper) {
        return provinceDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<Province> queryWrapper) {
        return provinceDao.selectCount(queryWrapper);
    }

    @Override
    public List<Province> selectList(Wrapper<Province> queryWrapper) {
        return provinceDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<Province> queryWrapper) {
        return provinceDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<Province> queryWrapper) {
        return provinceDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<Province> selectPage(IPage<Province> page, Wrapper<Province> queryWrapper) {
        return provinceDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<Province> page, Wrapper<Province> queryWrapper) {
        return provinceDao.selectMapsPage(page, queryWrapper);
    }
}

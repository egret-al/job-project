package com.service.payment.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.payment.TransferRecordDao;
import com.entity.payment.TransferRecord;
import com.service.payment.TransferRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 冉堃赤
 * @date 2020/3/31 8:58
 */
@Service("transferRecordService")
public class TransferRecordServiceImpl implements TransferRecordService {

    @Autowired
    private TransferRecordDao transferRecordDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(TransferRecord entity) {
        return transferRecordDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return transferRecordDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return transferRecordDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<TransferRecord> wrapper) {
        return transferRecordDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return transferRecordDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(TransferRecord entity) {
        return transferRecordDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(TransferRecord entity, Wrapper<TransferRecord> updateWrapper) {
        return transferRecordDao.update(entity, updateWrapper);
    }

    @Override
    public TransferRecord selectById(Serializable id) {
        return transferRecordDao.selectById(id);
    }

    @Override
    public List<TransferRecord> selectBatchIds(Collection<? extends Serializable> idList) {
        return transferRecordDao.selectBatchIds(idList);
    }

    @Override
    public List<TransferRecord> selectByMap(Map<String, Object> columnMap) {
        return transferRecordDao.selectByMap(columnMap);
    }

    @Override
    public TransferRecord selectOne(Wrapper<TransferRecord> queryWrapper) {
        return transferRecordDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<TransferRecord> queryWrapper) {
        return transferRecordDao.selectCount(queryWrapper);
    }

    @Override
    public List<TransferRecord> selectList(Wrapper<TransferRecord> queryWrapper) {
        return transferRecordDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TransferRecord> queryWrapper) {
        return transferRecordDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<TransferRecord> queryWrapper) {
        return transferRecordDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<TransferRecord> selectPage(IPage<TransferRecord> page, Wrapper<TransferRecord> queryWrapper) {
        return transferRecordDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TransferRecord> page, Wrapper<TransferRecord> queryWrapper) {
        return transferRecordDao.selectMapsPage(page, queryWrapper);
    }
}

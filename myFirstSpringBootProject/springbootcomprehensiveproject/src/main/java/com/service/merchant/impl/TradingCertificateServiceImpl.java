package com.service.merchant.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.merchant.TradingCertificateDao;
import com.entity.merchant.TradingCertificate;
import com.service.merchant.TradingCertificateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * @author 冉堃赤
 * @date 2020/4/5 23:10
 */
@Service("tradingCertificateService")
public class TradingCertificateServiceImpl implements TradingCertificateService {

    @Autowired
    private TradingCertificateDao tradingCertificateDao;

    @Value("${file-server-location}")
    private String fileServerLocation;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<TradingCertificate> selectTradingCertificateListByJobId(Integer id) {
        return tradingCertificateDao.selectTradingCertificateListByJobId(id);
    }

    @Override
    public List<TradingCertificate> selectTradingCertificateListByUserId(Integer id) {
        return tradingCertificateDao.selectTradingCertificateListByUserId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registeredCertificate(MultipartFile file, String userId) {

        String fileName = file.getOriginalFilename();

        //uuid设置文件名的唯一性
        fileName = UUID.randomUUID().toString() + "_" + fileName;
        int size = (int) file.getSize();
        System.out.println(fileName + ":" + size);
        //获取文件服务器的物理路径
        String path = fileServerLocation;
        File dest = new File(path + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        logger.info("上传的文件名：" + fileName);
        logger.info("文件后缀：" + fileName.substring(fileName.lastIndexOf(".")));

        try {
            //保存文件
            file.transferTo(dest);
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
        logger.info("文件上传成功");

        TradingCertificate tradingCertificate = new TradingCertificate();
        tradingCertificate.setUploadTime(new Date());
        tradingCertificate.setFilePath(path + fileName);
        tradingCertificate.setUserId(Integer.valueOf(userId));

        int influence = tradingCertificateDao.insert(tradingCertificate);
        logger.info("影响行数：" + influence);
        return influence;
    }

    @Override
    public TradingCertificate selectTradingCertificateById(Integer id) {
        return tradingCertificateDao.selectTradingCertificateById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(TradingCertificate entity) {
        return tradingCertificateDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return tradingCertificateDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return tradingCertificateDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<TradingCertificate> wrapper) {
        return tradingCertificateDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return tradingCertificateDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(TradingCertificate entity) {
        return tradingCertificateDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(TradingCertificate entity, Wrapper<TradingCertificate> updateWrapper) {
        return tradingCertificateDao.update(entity, updateWrapper);
    }

    @Override
    public TradingCertificate selectById(Serializable id) {
        return tradingCertificateDao.selectById(id);
    }

    @Override
    public List<TradingCertificate> selectBatchIds(Collection<? extends Serializable> idList) {
        return tradingCertificateDao.selectBatchIds(idList);
    }

    @Override
    public List<TradingCertificate> selectByMap(Map<String, Object> columnMap) {
        return tradingCertificateDao.selectByMap(columnMap);
    }

    @Override
    public TradingCertificate selectOne(Wrapper<TradingCertificate> queryWrapper) {
        return tradingCertificateDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<TradingCertificate> queryWrapper) {
        return tradingCertificateDao.selectCount(queryWrapper);
    }

    @Override
    public List<TradingCertificate> selectList(Wrapper<TradingCertificate> queryWrapper) {
        return tradingCertificateDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TradingCertificate> queryWrapper) {
        return tradingCertificateDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<TradingCertificate> queryWrapper) {
        return tradingCertificateDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<TradingCertificate> selectPage(IPage<TradingCertificate> page, Wrapper<TradingCertificate> queryWrapper) {
        return tradingCertificateDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TradingCertificate> page, Wrapper<TradingCertificate> queryWrapper) {
        return tradingCertificateDao.selectMapsPage(page, queryWrapper);
    }
}

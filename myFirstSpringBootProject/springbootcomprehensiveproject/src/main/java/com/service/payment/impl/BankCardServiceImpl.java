package com.service.payment.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.payment.BankCardDao;
import com.dao.payment.TransferRecordDao;
import com.entity.payment.BankCard;
import com.entity.payment.TransferRecord;
import com.exception.NotSufficientFundsException;
import com.service.payment.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 银行卡业务层，将会实现事务处理，避免出现
 * 网络差异导致因事务的问题出现数据不准确的
 * 情况
 * @author 冉堃赤
 * @date 2020/3/29 10:52
 */
@Service("bankCardService")
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private BankCardDao bankCardDao;

    @Autowired
    private TransferRecordDao transferRecordDao;

    /**
     * 银行卡转账
     * @param from 转出银行卡
     * @param to 转入银行卡
     * @param count 数量
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int transferAccounts(String from, String to, int count) throws Exception {
        BankCard fromCard = bankCardDao.selectById(from);
        BankCard toCard = bankCardDao.selectById(to);
        int r1 = 0, r2 = 0, r3 = 0;
        fromCard.setBalance(fromCard.getBalance() - count);
        if (fromCard.getBalance() < 0) {
            throw new NotSufficientFundsException(fromCard.getCardNumber() + "余额不足！");
        } else {
            //扣钱
            r1 = bankCardDao.updateById(fromCard);
            //模拟网络异常
//            int t = 1 / 0;
            //加钱
            toCard.setBalance(toCard.getBalance() + count);
            r2 = bankCardDao.updateById(toCard);
            //做表记录
            TransferRecord transferRecord = new TransferRecord();
            transferRecord.setCardFrom(from);
            transferRecord.setCardTo(to);
            transferRecord.setTransferCount(count);
            transferRecord.setTransferTime(new Date());
            r3 = transferRecordDao.insert(transferRecord);

        }
        return r1 + r2 + r3;
    }

    @Override
    public List<BankCard> selectAllBankCard() {
        return bankCardDao.selectAllBankCard();
    }

    @Override
    public BankCard selectBankCardByNumber(String num) {
        return bankCardDao.selectBankCardByNumber(num);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(BankCard entity) {
        return bankCardDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Serializable id) {
        return bankCardDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMap(Map<String, Object> columnMap) {
        return bankCardDao.deleteByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Wrapper<BankCard> wrapper) {
        return bankCardDao.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return bankCardDao.deleteBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(BankCard entity) {
        return bankCardDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(BankCard entity, Wrapper<BankCard> updateWrapper) {
        return bankCardDao.update(entity, updateWrapper);
    }

    @Override
    public BankCard selectById(Serializable id) {
        return bankCardDao.selectById(id);
    }

    @Override
    public List<BankCard> selectBatchIds(Collection<? extends Serializable> idList) {
        return bankCardDao.selectBatchIds(idList);
    }

    @Override
    public List<BankCard> selectByMap(Map<String, Object> columnMap) {
        return bankCardDao.selectByMap(columnMap);
    }

    @Override
    public BankCard selectOne(Wrapper<BankCard> queryWrapper) {
        return bankCardDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<BankCard> queryWrapper) {
        return bankCardDao.selectCount(queryWrapper);
    }

    @Override
    public List<BankCard> selectList(Wrapper<BankCard> queryWrapper) {
        return bankCardDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<BankCard> queryWrapper) {
        return bankCardDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<BankCard> queryWrapper) {
        return bankCardDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<BankCard> selectPage(IPage<BankCard> page, Wrapper<BankCard> queryWrapper) {
        return bankCardDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<BankCard> page, Wrapper<BankCard> queryWrapper) {
        return bankCardDao.selectMapsPage(page, queryWrapper);
    }
}

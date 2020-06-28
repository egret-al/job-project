package com.service.payment;

import com.entity.payment.BankCard;
import com.service.base.BaseMapperService;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/29 10:51
 */
public interface BankCardService extends BaseMapperService<BankCard> {

    /**
     * 转账
     * @param from 转出银行卡
     * @param to 转入银行卡
     * @param count 数量
     */
    int transferAccounts(String from, String to, int count) throws Exception;

    List<BankCard> selectAllBankCard();

    BankCard selectBankCardByNumber(String num);
}

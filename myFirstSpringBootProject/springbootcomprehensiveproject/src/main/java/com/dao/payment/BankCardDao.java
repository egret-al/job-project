package com.dao.payment;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.payment.BankCard;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/29 10:37
 */
@Mapper
@Repository("bankCardDao")
public interface BankCardDao extends BaseMapper<BankCard> {

    /**
     * 根据银行卡号码，查询银行卡，并且不使用懒加载，便于获取user的信息
     * @param num 银行卡号码
     * @return 银行卡
     */
    @Select("SELECT * FROM t_bank_card WHERE card_number=#{num}")
    @Results(id = "bankCardMapper", value = {
            @Result(id = true, property = "cardNumber", column = "card_number"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "userId", column = "u_id"),
            @Result(property = "user", column = "u_id", many = @Many(
                    select = "com.dao.authentication.UserDao.selectUserById", fetchType = FetchType.EAGER
            ))
    })
    BankCard selectBankCardByNumber(String num);

    /**
     * 级联查询全部银行卡
     * @return 银行卡集合
     */
    @ResultMap("bankCardMapper")
    @Select("SELECT * FROM t_bank_card")
    List<BankCard> selectAllBankCard();
}

package com.dao.merchant;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.merchant.TradingCertificate;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/4/5 23:02
 */
@Mapper
@Repository("tradingCertificateDao")
public interface TradingCertificateDao extends BaseMapper<TradingCertificate> {

    /**
     * 根据id查询营业证书，级联映射到user
     * @param id
     * @return
     */
    @Select("SELECT * FROM t_trading_certificate WHERE id=#{id}")
    @Results(id = "tradingCertificateMapper", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userId", column = "uid"),
            @Result(property = "filePath", column = "file_path"),
            @Result(property = "upload_time", column = "uploadTime"),
            @Result(property = "user", column = "uid", many = @Many(
                    select = "com.dao.authentication.UserDao.selectUserById", fetchType = FetchType.EAGER
            ))
    })
    TradingCertificate selectTradingCertificateById(Integer id);

    /**
     * 根据用户id查询证书
     * @param id
     * @return
     */
    @ResultMap("tradingCertificateMapper")
    @Select("SELECT * FROM t_trading_certificate WHERE uid=#{id}")
    List<TradingCertificate> selectTradingCertificateListByUserId(Integer id);

    /**
     * 根据job_id查询证书
     * @param id
     * @return
     */
    @ResultMap("tradingCertificateMapper")
    @Select("SELECT * FROM t_trading_certificate WHERE uid IN(" +
            "SELECT id FROM t_user WHERE t_account IN(" +
            "SELECT username FROM t_job WHERE id=#{id}))")
    List<TradingCertificate> selectTradingCertificateListByJobId(Integer id);
}

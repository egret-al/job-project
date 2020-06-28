package com.dao.payment;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.payment.TransferRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 冉堃赤
 * @date 2020/3/31 8:56
 */
@Mapper
@Repository("transferRecordDao")
public interface TransferRecordDao extends BaseMapper<TransferRecord> {


}

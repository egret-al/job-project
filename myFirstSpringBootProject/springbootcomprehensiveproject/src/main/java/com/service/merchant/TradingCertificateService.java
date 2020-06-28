package com.service.merchant;

import com.entity.merchant.TradingCertificate;
import com.service.base.BaseMapperService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/4/5 23:10
 */
public interface TradingCertificateService extends BaseMapperService<TradingCertificate> {

    List<TradingCertificate> selectTradingCertificateListByJobId(Integer id);

    List<TradingCertificate> selectTradingCertificateListByUserId(Integer id);

    int registeredCertificate(MultipartFile file, String userId);

    TradingCertificate selectTradingCertificateById(Integer id);
}

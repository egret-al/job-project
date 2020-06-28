package com.utils.performance;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.Map;

/**
 * 内存信息
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/6 20:07
 */
public class MemoryInformation extends BaseSystemInformation {

    public MemoryInformation() {
        try {
            refreshProperty();
        } catch (Exception e) {
            this.logger.error(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getProperty() throws Exception {
        this.refreshProperty();
        return this.resultInfo;
    }

    @Override
    public void refreshProperty() throws Exception {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        //内存总量
        this.resultInfo.put("MemoryTotal", mem.getTotal() / 1024L);
        //当前内存使用量
        this.resultInfo.put("MemoryUsed", mem.getUsed() / 1024L);
        //当前内存剩余量
        this.resultInfo.put("MemoryFree", mem.getFree() / 1024L);

        Swap swap = sigar.getSwap();
        //交换区总量
        this.resultInfo.put("SwapTotal", swap.getTotal() / 1024L);
        //当前交换区使用量
        this.resultInfo.put("SwapUsed", swap.getUsed() / 1024L);
        //当前交换区剩余量
        this.resultInfo.put("SwapFree", swap.getFree() / 1024L);
    }
}

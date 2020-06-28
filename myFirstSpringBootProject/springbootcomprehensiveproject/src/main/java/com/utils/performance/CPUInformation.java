package com.utils.performance;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;

import java.util.Map;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/7 11:24
 */
public class CPUInformation extends BaseSystemInformation {

    public CPUInformation() {
        try {
            refreshProperty();
        } catch (Exception e) {
            this.logger.error("当前系统不支持");
        }
    }

    @Override
    public void refreshProperty() throws Exception {
        Sigar sigar = new Sigar();
        CpuInfo[] infos = sigar.getCpuInfoList();
        CpuPerc[] cpuList;
        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < infos.length; i++) {
            CpuInfo info = infos[i];
            //CPU的总量MHz
            this.resultInfo.put("CPU" + (i + 1) + "Mhz", info.getMhz());
            //CPU生产商
            this.resultInfo.put("CPU" + (i + 1) + "Vendor", info.getVendor());
            //CPU类别
            this.resultInfo.put("CPU" + (i + 1) + "Model", info.getModel());
            //CPU缓存数量
            this.resultInfo.put("CPU" + (i + 1) + "CacheSize", info.getCacheSize());

            //----------------------------------------------------------------------------------

            //CPU的用户使用率
            this.resultInfo.put("CPU" + (i + 1) + "User", CpuPerc.format(cpuList[i].getUser()));
            //CPU系统使用率
            this.resultInfo.put("CPU" + (i + 1) + "Sys", CpuPerc.format(cpuList[i].getSys()));
            //CPU当前等待率
            this.resultInfo.put("CPU" + (i + 1) + "Wait", CpuPerc.format(cpuList[i].getWait()));
            //CPU错误率
            this.resultInfo.put("CPU" + (i + 1) + "Nice", CpuPerc.format(cpuList[i].getNice()));
            //CPU当前空闲率
            this.resultInfo.put("CPU" + (i + 1) + "Idle", CpuPerc.format(cpuList[i].getIdle()));
            //CPU总的使用率
            this.resultInfo.put("CPU" + (i + 1) + "Combined", CpuPerc.format(cpuList[i].getCombined()));
        }
    }

    @Override
    public Map<String, Object> getProperty() throws Exception {
        refreshProperty();
        return this.resultInfo;
    }
}

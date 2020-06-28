package com.utils.performance;

import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.Map;

/**
 * 以太网信息
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/6 21:28
 */
public class EthernetInformation extends BaseSystemInformation {

    public EthernetInformation() {
        try {
            refreshProperty();
        } catch (Exception e) {
            this.logger.error(e.getMessage());
        }
    }

    @Override
    public void refreshProperty() throws Exception {
        Sigar sigar = new Sigar();
        String[] faces = sigar.getNetInterfaceList();

        for (int i = 0; i < faces.length; i++) {
            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(faces[i]);
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            //IP地址
            this.resultInfo.put(cfg.getName() + " Address", cfg.getAddress());
            //广播地址
            this.resultInfo.put(cfg.getName() + " Broadcast", cfg.getBroadcast());
            //网卡MAC地址
            this.resultInfo.put(cfg.getName() + " Hwaddr", cfg.getHwaddr());
            //子网掩码
            this.resultInfo.put(cfg.getName() + " Netmask", cfg.getNetmask());
            //网卡描述信息
            this.resultInfo.put(cfg.getName() + " Description", cfg.getDescription());
            //网卡类型
            this.resultInfo.put(cfg.getName() + " Type", cfg.getType());
        }
    }

    @Override
    public Map<String, Object> getProperty() throws Exception {
        return this.resultInfo;
    }
}

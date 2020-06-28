package com.utils.performance;

import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.Map;

/**
 * 网络系统信息
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/6 21:17
 */
public class NetInformation extends BaseSystemInformation {

    public NetInformation() {
        try {
            this.refreshProperty();
        } catch (Exception e) {
            this.logger.error(e.getMessage());
        }
    }

    @Override
    public void refreshProperty() throws Exception {
        Sigar sigar = new Sigar();
        String[] ifNames = sigar.getNetInterfaceList();
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifConfig = sigar.getNetInterfaceConfig(name);
            this.resultInfo.put(name + " Address", ifConfig.getAddress());
            this.resultInfo.put(name + " Netmask", ifConfig.getNetmask());

            if ((ifConfig.getFlags() & 1L) <= 0L) {
//                System.out.println("!IFF_UP...skipping getNetInterfaceStat");
                continue;
            }
            NetInterfaceStat ifStat = sigar.getNetInterfaceStat(name);
            //接收的总包裹数
            this.resultInfo.put(name + " RxPackets", ifStat.getRxPackets());
            //发送的总包裹数
            this.resultInfo.put(name + " TxPackets", ifStat.getTxPackets());
            //接收到的总字节数
            this.resultInfo.put(name + " RxBytes", ifStat.getRxBytes());
            //发送的总字节数
            this.resultInfo.put(name + " TxBytes", ifStat.getTxBytes());
            //接收到的错误包数
            this.resultInfo.put(name + " RxErrors", ifStat.getRxErrors());
            //发送数据包时的错误数
            this.resultInfo.put(name + " TxErrors", ifStat.getTxErrors());
            //接收时丢弃的包数
            this.resultInfo.put(name + " RxDropped", ifStat.getRxDropped());
            //发送时丢弃的包数
            this.resultInfo.put(name + " TxDropped", ifStat.getTxDropped());
        }
    }

    @Override
    public Map<String, Object> getProperty() throws Exception {
        refreshProperty();
        return this.resultInfo;
    }
}

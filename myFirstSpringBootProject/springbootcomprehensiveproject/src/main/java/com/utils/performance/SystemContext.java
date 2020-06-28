package com.utils.performance;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * 系统信息管理的上下文对象，维护了所有系统信息的类，
 * 采用单例模式（双重检查避免高并发信息的错误），使
 * 用懒加载机制
 * 已经注入到IoC中，因此只需要在使用的地方依赖注入
 * 就可以使用该上下文
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/6 20:37
 */
@Component
public class SystemContext {

    private volatile BaseSystemInformation osInformation;
    private volatile BaseSystemInformation memoryInformation;
    private volatile BaseSystemInformation fileInformation;
    private volatile BaseSystemInformation netInformation;
    private volatile BaseSystemInformation ethernetInformation;
    private volatile BaseSystemInformation cpuInformation;

    public BaseSystemInformation getOsInformation() {
        if (osInformation == null) {
            synchronized (SystemContext.class) {
                if (osInformation == null) {
                    osInformation = new OSInformation();
                }
            }
        }
        return osInformation;
    }

    public BaseSystemInformation getMemoryInformation() {
        if (memoryInformation == null) {
            synchronized (SystemContext.class) {
                if (memoryInformation == null) {
                    memoryInformation = new MemoryInformation();
                }
            }
        }
        return memoryInformation;
    }

    public BaseSystemInformation getFileInformation() {
        if (fileInformation == null) {
            synchronized (SystemContext.class) {
                if (fileInformation == null) {
                    fileInformation = new FileInformation();
                }
            }
        }
        return fileInformation;
    }

    public BaseSystemInformation getNetInformation() {
        if (netInformation == null) {
            synchronized (SystemContext.class) {
                if (netInformation == null) {
                    netInformation = new NetInformation();
                }
            }
        }
        return netInformation;
    }

    public BaseSystemInformation getEthernetInformation() {
        if (ethernetInformation == null) {
            synchronized (SystemContext.class) {
                if (ethernetInformation == null) {
                    ethernetInformation = new EthernetInformation();
                }
            }
        }
        return ethernetInformation;
    }

    public BaseSystemInformation getCpuInformation() {
        if (cpuInformation == null) {
            synchronized (SystemContext.class) {
                if (cpuInformation == null) {
                    cpuInformation = new CPUInformation();
                }
            }
        }
        return cpuInformation;
    }
}

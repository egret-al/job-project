package com.utils.performance;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.Map;

/**
 * 文件系统信息
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/6 20:57
 */
public class FileInformation extends BaseSystemInformation {

    public FileInformation() {
        try {
            refreshProperty();
        } catch (Exception e) {
            this.logger.error(e.getMessage());
        }
    }

    @Override
    public void refreshProperty() throws Exception {
        Sigar sigar = new Sigar();
        FileSystem[] fsList = sigar.getFileSystemList();

        for (int i = 0; i < fsList.length; i++) {
            FileSystem fs = fsList[i];
            FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());

            switch (fs.getType()) {
                case 0: // TYPE_UNKNOWN ：未知
                    break;
                case 1: // TYPE_NONE
                    break;
                case 2: // TYPE_LOCAL_DISK : 本地硬盘
                    // 文件系统总大小
                    this.resultInfo.put(fs.getDevName() + "Total", usage.getTotal() + "KB");
                    // 文件系统剩余大小
                    this.resultInfo.put(fs.getDevName() + "Free", usage.getFree() + "KB");
                    // 文件系统可用大小
                    this.resultInfo.put(fs.getDevName() + "Avail", usage.getAvail() + "KB");
                    // 文件系统已经使用量
                    this.resultInfo.put(fs.getDevName() + "Used", usage.getUsed() + "KB");
                    double usePercent = usage.getUsePercent() * 100D;
                    // 文件系统资源的利用率
                    this.resultInfo.put(fs.getDevName() + "Percent", usePercent + "%");
                    break;
                case 3:// TYPE_NETWORK ：网络
                    break;
                case 4:// TYPE_RAM_DISK ：闪存
                    break;
                case 5:// TYPE_CDROM ：光驱
                    break;
                case 6:// TYPE_SWAP ：页面交换
                    break;
            }
            this.resultInfo.put(fs.getDevName() + "DiskReads", usage.getDiskReads());
            this.resultInfo.put(fs.getDevName() + "DiskWrites", usage.getDiskWrites());
        }
    }

    @Override
    public Map<String, Object> getProperty() throws Exception {
        this.refreshProperty();
        return this.resultInfo;
    }
}

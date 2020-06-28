package com.utils.performance;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 操作系统信息
 * 对于操作系统而言，信息基本都是固定的，因此不需要每次都查询
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/6 19:45
 */
public class OSInformation extends BaseSystemInformation {

    public OSInformation() {
        try {
            refreshProperty();
        } catch (Exception e) {
            this.logger.error(e.getMessage());
        }
    }

    /**
     * 系统数据不会刷新，因此直接返回初始化数据
     * @return 系统信息
     */
    @Override
    public Map<String, Object> getProperty() throws Exception {
        return this.resultInfo;
    }

    /**
     * 获取操作系统的信息
     */
    @Override
    public void refreshProperty() throws Exception {
        //获取能得到操作系统信息的map
        Map<String, String> map = System.getenv();
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr = InetAddress.getLocalHost();


        this.resultInfo.put("username", map.get("USERNAME"));
        this.resultInfo.put("serverName", map.get("COMPUTERNAME"));
        this.resultInfo.put("domain", map.get("USERDOMAIN"));

        assert addr != null;
        this.resultInfo.put("HostAddress", addr.getHostAddress());
        this.resultInfo.put("HostName", addr.getHostName());
        //JVM可以使用的总内存
        this.resultInfo.put("totalMemory", r.totalMemory());
        //JVM可以使用的剩余内存
        this.resultInfo.put("freeMemory", r.freeMemory());
        //JVM可以使用的处理器个数
        this.resultInfo.put("availableProcessors", r.availableProcessors());
        //Java的运行环境版本
        this.resultInfo.put("version", props.getProperty("java.version"));
        //Java的运行环境供应商
        this.resultInfo.put("vendor", props.getProperty("java.vendor"));
        //Java供应商的URL
        this.resultInfo.put("vendorUrl", props.getProperty("java.vendor.url"));
        //Java的安装路径
        this.resultInfo.put("JavaHome", props.getProperty("java.home"));
        //Java的虚拟机规范版本
        this.resultInfo.put("vmSpecificationVersion", props.getProperty("java.vm.specification.version"));
        //Java的虚拟机规范供应商
        this.resultInfo.put("vmSpecificationVendor", props.getProperty("java.vm.specification.vendor"));
        //Java的虚拟机规范名称
        this.resultInfo.put("vmSpecificationName", props.getProperty("java.vm.specification.name"));
        //Java的虚拟机实现版本
        this.resultInfo.put("VMVersion", props.getProperty("java.vm.version"));
        //Java的虚拟机实现供应商
        this.resultInfo.put("VMVendor", props.getProperty("java.vm.vendor"));
        //Java的虚拟机实现名称
        this.resultInfo.put("VMName", props.getProperty("java.vm.name"));
        //Java运行时环境规范版本
        this.resultInfo.put("specificationVersion", props.getProperty("java.specification.version"));
        //Java运行时环境规范供应商
        this.resultInfo.put("specificationVendor", props.getProperty("java.specification.vendor"));
        //Java运行时环境规范名称
        this.resultInfo.put("specificationName", props.getProperty("java.specification.name"));
        //Java的类格式版本号
        this.resultInfo.put("classVersion", props.getProperty("java.class.version"));
        //Java的类路径
        this.resultInfo.put("classPath", props.getProperty("java.class.path"));
        //加载库时搜索的路径列表
        this.resultInfo.put("libraryPath", props.getProperty("java.library.path"));
        //默认的临时文件路径
        this.resultInfo.put("ioTmpdir", props.getProperty("java.io.tmpdir"));
        //一个或多个扩展目录的路径
        this.resultInfo.put("extDirs", props.getProperty("java.ext.dirs"));
        //操作系统的名称
        this.resultInfo.put("osName", props.getProperty("os.name"));
        //操作系统的构架
        this.resultInfo.put("osArch", props.getProperty("os.arch"));
        //操作系统的版本
        this.resultInfo.put("osVersion", props.getProperty("os.version"));
        //文件分隔符
        this.resultInfo.put("fileSeparator", props.getProperty("file.separator"));
        //路径分隔符
        this.resultInfo.put("pathSeparator", props.getProperty("path.separator"));
        //行分隔符
        this.resultInfo.put("lineSeparator", props.getProperty("line.separator"));
        //用户的账户名称
        this.resultInfo.put("userName", props.getProperty("user.name"));
        //用户的主目录
        this.resultInfo.put("userHome", props.getProperty("user.home"));
        //用户的当前工作目录
        this.resultInfo.put("userDir", props.getProperty("user.dir"));
    }

}

package com.controller.other;

import com.utils.performance.SystemContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/7 11:51
 */
@RestController
public class SystemInfoController {

    @Autowired
    private SystemContext systemContext;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取CPU信息
     * @return
     */
    @RequestMapping(value = "/getCPUInformation", method = RequestMethod.POST)
    public Map<String, Object> getCPUInformation() throws Exception {
        return systemContext.getCpuInformation().getProperty();
    }

    /**
     * 获取以太网信息
     * @return
     */
    @RequestMapping(value = "/getEthernetInformation", method = RequestMethod.POST)
    public Map<String, Object> getEthernetInformation() throws Exception {
        return systemContext.getEthernetInformation().getProperty();
    }

    /**
     * 获取文件系统信息
     * @return
     */
    @RequestMapping(value = "/getFileInformation", method = RequestMethod.POST)
    public Map<String, Object> getFileInformation() throws Exception {
        return systemContext.getFileInformation().getProperty();
    }

    /**
     * 获取内存信息
     * @return
     */
    @RequestMapping(value = "/getMemoryInformation", method = RequestMethod.POST)
    public Map<String, Object> getMemoryInformation() throws Exception {
        return systemContext.getMemoryInformation().getProperty();
    }

    /**
     * 获取网络信息
     * @return
     */
    @RequestMapping(value = "/getNetInformation", method = RequestMethod.POST)
    public Map<String, Object> getNetInformation() throws Exception {
        return systemContext.getNetInformation().getProperty();
    }

    /**
     * 获取系统信息
     * @return
     */
    @RequestMapping(value = "/getOSInformation", method = RequestMethod.POST)
    public Map<String, Object> getOSInformation() throws Exception {
        return systemContext.getOsInformation().getProperty();
    }

}

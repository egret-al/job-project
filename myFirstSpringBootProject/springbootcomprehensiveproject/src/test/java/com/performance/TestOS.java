package com.performance;

import com.SpringbootApplication;
import com.utils.performance.MemoryInformation;
import com.utils.performance.OSInformation;
import com.utils.performance.SystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;
import java.util.Set;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/6 20:15
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestOS {

    @Autowired
    private SystemContext systemContext;

    @Test
    public void testCPUInformation() throws Exception {
        Map<String, Object> resultInfo = systemContext.getCpuInformation().getProperty();
        Set<String> keySet = resultInfo.keySet();
        for (String key : keySet) {
            System.out.println(key + ": " + resultInfo.get(key));
        }
    }


    @Test
    public void testEthernetInformation() throws Exception {
        Map<String, Object> resultInfo = systemContext.getEthernetInformation().getProperty();
        Set<String> keySet = resultInfo.keySet();
        for (String key : keySet) {
            System.out.println(key + ": " + resultInfo.get(key));
        }
    }

    @Test
    public void testNetInformation() throws Exception {
        Map<String, Object> resultInfo = systemContext.getNetInformation().getProperty();
        Set<String> keySet = resultInfo.keySet();
        for (String key : keySet) {
            System.out.println(key + ": " + resultInfo.get(key));
        }
    }

    @Test
    public void testOSInformation() throws Exception {
        Map<String, Object> resultInfo = systemContext.getOsInformation().getProperty();
        Set<String> keySet = resultInfo.keySet();
        for (String key : keySet) {
            System.out.println(key + ": " + resultInfo.get(key));
        }
    }

    @Test
    public void testFileInformation() throws Exception {
        Map<String, Object> resultInfo = systemContext.getFileInformation().getProperty();
        Set<String> keySet = resultInfo.keySet();
        for (String key : keySet) {
            System.out.println(key + ": " + resultInfo.get(key));
        }
    }

    @Test
    public void testMemoryInformation() throws Exception {
        Map<String, Object> resultInfo = systemContext.getMemoryInformation().getProperty();
        Set<String> keySet = resultInfo.keySet();
        for (String key : keySet) {
            System.out.println(key + ": " + resultInfo.get(key));
        }
    }
}

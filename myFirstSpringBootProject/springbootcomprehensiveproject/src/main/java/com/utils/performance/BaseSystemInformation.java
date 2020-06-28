package com.utils.performance;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/6 19:41
 */
public abstract class BaseSystemInformation {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Getter
    protected Map<String, Object> resultInfo = new HashMap<String, Object>(30);

    public abstract void refreshProperty() throws Exception;

    public abstract Map<String, Object> getProperty() throws Exception;
}

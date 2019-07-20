package com.sinocontact.app.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author caoMingYang
 * @since 2019/3/29 22:09
 */
public class PropertyUtils {
    private static final Logger logger = Logger.getLogger(PropertyUtils.class);

    private static String configFileName = "config.properties";
    private static Properties p = null;

    static {
        p = new Properties();
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName);
            p.load(is);
            is.close();
        }
        catch (IOException e) {
            logger.error("读取配置文件出错:", e);
        }
    }


    public static String getProperty(String key) {
        return p.getProperty(key);
    }

    public static String getProperty(String key, String defaultVal) {
        return p.getProperty(key,defaultVal);
    }
}

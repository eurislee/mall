package me.euris.mall.util;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-09-2023 18:46:00
 */

@Slf4j
public class PropertyUtil {
    private static Properties props;

    static {
        String fileName = "mall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertyUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常", e);
        }
    }

    public static String getProperty(String key) {
        String string = "1";
        string = new String();


        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key, String defaultValue) {

        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            value = defaultValue;
        }
        return value.trim();
    }

    public static Integer getPropertyAsInt(String key){
        String valueStr = props.getProperty(key.trim());
        if (StringUtils.isBlank(valueStr)) {
            return 0;
        }
        try{
            Integer value = Integer.parseInt(valueStr);
            return value;
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public static Integer getPropertyAsInt(String key, Integer defaultValue){
        String valueStr = props.getProperty(key.trim());
        if (StringUtils.isBlank(valueStr)) {
            return defaultValue;
        }
        try{
            Integer value = Integer.parseInt(valueStr);
            return value;
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static Long getPropertyAsLong(String key){
        String valueStr = props.getProperty(key.trim());
        if (StringUtils.isBlank(valueStr)) {
            return 0L;
        }
        try{
            Long value = Long.parseLong(valueStr);
            return value;
        }catch (NumberFormatException e){
            return 0L;
        }
    }


    public static Long getPropertyAsLong(String key, Long defaultValue){
        String valueStr = props.getProperty(key.trim());
        if (StringUtils.isBlank(valueStr)) {
            return defaultValue;
        }
        try{
            Long value = Long.parseLong(valueStr);
            return value;
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }
}

package me.euris.mall.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-09-2023 18:57:00
 */

@Slf4j
public class JacksonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        //对象的所有非空字段序列化
        MAPPER.setSerializationInclusion(Include.NON_EMPTY);
        //取消自动转timestamps
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //忽略空Bean转json的错误
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        //忽略　在json字符串中存在,但是java对象中不存在对应属性的情况,防止报错
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        //日期格式
        MAPPER.setDateFormat(new SimpleDateFormat(DateUtil.DATETIME_FORMATTER));
    }

    public static <T> String objToString (T obj){
        if(obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("parse object to String error ",e);
            return null;
        }
    }

    public static <T> String objToStringPretty (T obj){
        if(obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("parse object to String error ",e);
            return null;
        }
    }

    public static <T> T stringToObj(String str,Class<T> cls){
        if (StringUtils.isEmpty(str) || cls == null) {
            return null;
        }
        try {
            return cls.equals(String.class) ? (T)str : MAPPER.readValue(str, cls);
        } catch (IOException e) {
            log.warn("parse String to Object error ",e);
            return null;
        }
    }

    public static <T> T stringToObj(String str, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return typeReference.getType().equals(String.class) ?  (T)str : MAPPER.readValue(str,typeReference);
        } catch (Exception e) {
            log.warn("parse String to Object error ",e);
            return null;
        }
    }

    public static <T> T stringToObj(String str,Class<?> collectionClass,Class<?>... beanClass){
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(collectionClass,beanClass);
        try {
            return MAPPER.readValue(str,javaType);
        } catch (Exception e) {
            log.warn("parse String to Object error ",e);
            return null;
        }
    }
}

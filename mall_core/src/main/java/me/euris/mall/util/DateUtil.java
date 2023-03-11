package me.euris.mall.util;

import org.apache.commons.lang3.StringUtils;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-09-2023 17:51:00
 */
public class DateUtil {

    public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMATTER = "HH:mm:ss";
    public static final String MONTH_FORMATTER = "MM-yyyy";
    public static final String SHORT_DATE_FORMATTER = "MM-dd-yy";
    public static final String SHORT_DATETIME_FORMATTER = "MM-dd-yy HH:mm:ss";
    public static final String DATE_FORMATTER = "MM-dd-yyyy";


    /**
     * @Name getCurrentDateTimeAsString
     * @Description 获取当前日期时间字符串
     * @Author Euris Lee
     * @param: pattern 格式化规则
     * @CreateTime 3/9/23 7:09 PM
     * @return: java.lang.String
     * @Throws
     */
    public static String getCurrentDateTimeAsString(DateTimeFormatter pattern){
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (pattern == null) {
            pattern = DateTimeFormatter.ofPattern(DATETIME_FORMATTER);
        }
        return currentDateTime.format(pattern);
    }

    /**
     * @Name getCurrentDateAsString
     * @Description 获取当前日期字符串
     * @Author Euris Lee 
     * @param: pattern 格式化规则
     * @CreateTime 3/9/23 7:18 PM 
     * @return: java.lang.String
     * @Throws
     */
    public static String getCurrentDateAsString(DateTimeFormatter pattern){
        LocalDate currentDate = LocalDate.now();
        if (pattern == null){
            pattern = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        }
        return currentDate.format(pattern);
    }

    /**
     * @Name getCurrentLocalDateTime
     * @Description 获取当前日期时间
     * @Author Euris Lee 
     * @param: pattern 格式化规则
     * @CreateTime 3/9/23 7:21 PM 
     * @return: java.time.LocalDateTime
     * @Throws
     */
    public static LocalDateTime getCurrentLocalDateTime(DateTimeFormatter pattern){
        String str = getCurrentDateTimeAsString(null);
        if (pattern == null){
            pattern = DateTimeFormatter.ofPattern(DATETIME_FORMATTER);
        }
        return LocalDateTime.parse(str, pattern);
    }
    
    
    /**
     * @Name getCurrentLocalDate
     * @Description 获取当前日期
     * @Author Euris Lee 
     * @param: pattern 格式化规则
     * @CreateTime 3/9/23 7:22 PM 
     * @return: java.time.LocalDate
     * @Throws
     */
    public static LocalDate getCurrentLocalDate(DateTimeFormatter pattern){
        String str = getCurrentDateAsString(null);
        if (pattern == null){
            pattern = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        }
        return LocalDate.parse(str, pattern);
    }

    /**
     * @Name getCurrentDate
     * @Description 获取当前日期
     * @Author Euris Lee
     * @param: formatter 格式化规则
     * @CreateTime 3/9/23 7:24 PM
     * @return: java.util.Date
     * @Throws
     */
    public static Date getCurrentDate(DateTimeFormatter formatter){
        LocalDateTime localDateTime = getCurrentLocalDateTime(formatter);
        return convertLocalDateTimeToDate(localDateTime);
    }

    /**
     * @Name convertLocalDateTimeToDate
     * @Description 转换LocalDatetime为Date对象
     * @Author Euris Lee 
     * @param: localDateTime
     * @CreateTime 3/9/23 7:23 PM 
     * @return: java.util.Date
     * @Throws
     */
    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }
    
    /**
     * @Name plusSecondBaseOnCurrentDate
     * @Description 在当前时间加秒数
     * @Author Euris Lee 
     * @param: second 秒(可为负数)
     * @CreateTime 3/9/23 7:24 PM 
     * @return: java.util.Date
     * @Throws
     */
    public static Date plusSecondBaseOnCurrentDate(int second){
        LocalDateTime currentLocalDateTime = getCurrentLocalDateTime(null);
        LocalDateTime plusSeconds = currentLocalDateTime.plusSeconds(second);
        return convertLocalDateTimeToDate(plusSeconds);
    }


    /**
     * @Name getDurationMinutes
     * @Description 计算两个日期的相差分钟
     * @Author Euris Lee 
     * @param: start 开始时间
     * @param: end 结束时间
     * @CreateTime 3/9/23 7:26 PM 
     * @return: java.lang.Long
     * @Throws
     */
    public static Long getDurationMinutes(Date start,Date end){
        Duration duration = Duration.between(start.toInstant(),end.toInstant());
        return duration.toMinutes();
    }

    /**
     * @Name parseLocalDateTime
     * @Description
     * @Author Euris Lee
     * @param: dateTimeStr
     * @param: pattern
     * @CreateTime 3/9/23 7:12 PM
     * @return: java.time.LocalDateTime
     * @Throws
     */
    public static LocalDateTime parseLocalDateTime(String dateTimeStr,String pattern){
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr){
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATETIME_FORMATTER));
    }

    public static String formatLocalDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return StringUtils.EMPTY;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return StringUtils.EMPTY;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(DATETIME_FORMATTER));
    }


}

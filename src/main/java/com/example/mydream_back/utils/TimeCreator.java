package com.example.mydream_back.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCreator {

    // 默认格式
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);

    // 常用格式常量（可扩展）
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final String PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * 获取当前时间对象
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前时间字符串，默认格式 yyyy-MM-dd HH:mm:ss
     */
    public static String nowStr() {
        return now().format(DEFAULT_FORMATTER);
    }

    /**
     * 获取当前时间字符串，指定格式
     * @param pattern 格式模板，如 "yyyy年MM月dd日 HH:mm"
     */
    public static String nowStr(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return now().format(formatter);
    }

    /**
     * 将 LocalDateTime 格式化为字符串
     * @param time 时间对象
     * @param pattern 格式模板
     */
    public static String format(LocalDateTime time, String pattern) {
        if (time == null || pattern == null || pattern.isEmpty()) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }

    /**
     * 获取当前日期字符串，格式为 yyyy-MM-dd
     */
    public static String today() {
        return now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * 获取当前时间字符串，格式为 HH:mm:ss
     */
    public static String currentTimeStr() {
        return now().format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * 获取当前时间戳（毫秒）
     */
    public static long timestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间戳（秒）
     */
    public static long timestampSec() {
        return System.currentTimeMillis() / 1000;
    }

}
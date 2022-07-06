package com.avedex.cc.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatUtil {
    /**
     * LocalDateTime转时间格式字符串
     *
     * @param localDateTime 时间
     * @return string
     */
    public static String formatToString(LocalDateTime localDateTime) {
        String format = "yyyy年MM月dd日HH时mm分ss秒";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String formatToString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 时间字符串 转LocalDateTime
     *
     * @param localDateTime 时间字符串
     * @return LocalDateTime
     */
    public static LocalDateTime stringToFormat(String localDateTime) {
        String format = "yyyy:MM:dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(localDateTime, dateTimeFormatter);
    }

}

package com.wangjie.bill.base.date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期解析/格式化工具
 */
@SuppressWarnings("WeakerAccess")
public abstract class DateFormatUtil {

    /**
     * 日期时间带毫秒格式-常规分隔
     */
    public static final String DATETIME_MILLS_SEP = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 日期时间带毫秒格式-无分隔
     */
    public static final String DATETIME_MILLS_NO_SEP = "yyyyMMddHHmmssSSS";
    /**
     * 日期时间格式-常规分隔
     */
    public static final String DATETIME_SEP = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期时间格式-无分隔
     */
    public static final String DATETIME_NO_SEP = "yyyyMMddHHmmss";
    /**
     * 日期格式-常规分隔
     */
    public static final String DATE_SEP = "yyyy-MM-dd";
    /**
     * 日期格式－无分隔
     */
    public static final String DATE_NO_SEP = "yyyyMMdd";

    /**
     * 日期时间格式-ISO 8601
     */
    public static final String DATETIME_ISO = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式日期串
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 日期时间带毫秒格式化-常规分隔<br>
     * 格式: yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param date 日期
     * @return 格式日期串
     */
    public static String formatDateTimeMills(Date date) {
        return format(date, DATETIME_MILLS_SEP);
    }

    /**
     * 日期时间ISO-8601格式
     *
     * @param date 日期
     * @return 格式日期串
     */
    public static String formatDateISO(Date date) {
        return format(date, DATETIME_ISO);
    }

    /**
     * 日期时间带毫秒格式化-无分隔<br>
     * 格式: yyyyMMddHHmmssSSS
     *
     * @param date 日期
     * @return 格式日期串
     */
    public static String formatDateTimeMillsNoSep(Date date) {
        return format(date, DATETIME_MILLS_NO_SEP);
    }

    /**
     * 日期时间格式化-常规分隔<br>
     * 格式: yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return 格式日期串
     */
    public static String formatDateTime(Date date) {
        return format(date, DATETIME_SEP);
    }

    /**
     * 日期时间格式化-无分隔<br>
     * 格式: yyyyMMddHHmmss
     *
     * @param date 日期
     * @return 格式日期串
     */
    public static String formatDateTimeNoSep(Date date) {
        return format(date, DATETIME_NO_SEP);
    }

    /**
     * 日期格式化-常规分隔<br>
     * 格式: yyyy-MM-dd
     *
     * @param date 日期
     * @return 格式日期串
     */
    public static String formatDate(Date date) {
        return format(date, DATE_SEP);
    }

    /**
     * 日期格式化-无分隔<br>
     * 格式: yyyyMMdd
     *
     * @param date 日期
     * @return 格式日期串
     */
    public static String formatDateNoSep(Date date) {
        return format(date, DATE_NO_SEP);
    }

    /**
     * 解析日期
     *
     * @param str     日期串
     * @param pattern 格式
     * @return 日期
     */
    public static Date parse(String str, String pattern) {
        Date date = null;
        try {
            date = DateUtils.parseDate(str, new String[]{pattern});
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    /**
     * 解析日期时间带毫秒-常规分隔<br>
     * 格式: yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param str 日期串
     * @return 日期
     */
    public static Date parseDateTimeMills(String str) {
        return parse(str, DATETIME_MILLS_SEP);
    }

    /**
     * 解析日期时间带毫秒-无分隔<br>
     * 格式: yyyyMMddHHmmssSSS
     *
     * @param str 日期串
     * @return 日期
     */
    public static Date parseDateTimeMillsNoSep(String str) {
        return parse(str, DATETIME_MILLS_NO_SEP);
    }

    /**
     * 解析日期时间-常规分隔<br>
     * 格式: yyyy-MM-dd HH:mm:ss
     *
     * @param str 日期串
     * @return 日期
     */
    public static Date parseDateTime(String str) {
        return parse(str, DATETIME_SEP);
    }

    /**
     * 解析日期时间-ISO-8601
     *
     * @param str 日期串
     * @return 日期
     */
    public static Date parseDateISO(String str) {
        return parse(str, DATETIME_ISO);
    }

    /**
     * 解析日期时间-无分隔<br>
     * 格式: yyyyMMddHHmmss
     *
     * @param str 日期串
     * @return 日期
     */
    public static Date parseDateTimeNoSep(String str) {
        return parse(str, DATETIME_NO_SEP);
    }

    /**
     * 解析日期-常规分隔<br>
     * 格式: yyyy-MM-dd
     *
     * @param str 日期串
     * @return 日期
     */
    public static Date parseDate(String str) {
        return parse(str, DATE_SEP);
    }

    /**
     * 解析日期-无分隔<br>
     * 格式: yyyyMMdd
     *
     * @param str 日期串
     * @return 日期
     */
    public static Date parseDateNoSep(String str) {
        return parse(str, DATE_NO_SEP);
    }
}

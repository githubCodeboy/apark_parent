package com.apark.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author johnny
 *
 */
public class DateUtil {

    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

    private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private static final ThreadLocal<DateFormat> dateTimeFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private static final ThreadLocal<DateFormat> timeFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };

    private static final ThreadLocal<DateFormat> yearMonthFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMM");
        }
    };

    private static final ThreadLocal<DateFormat> millisecondFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("hhmmssSSS");
        }
    };

    private static final ThreadLocal<DateFormat> YYYY_MMFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM");
        }
    };

    private static final ThreadLocal<DateFormat> YYYY_YEAR_MM_MONTH_Format = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月");
        }
    };

    public static String getDays(){
        return sdfDays.format(new Date());
    }

    public static String format(Date date) {
        return dateFormat.get().format(date);
    }

    public static Date parse(String str) throws ParseException {
        return dateFormat.get().parse(str);
    }

    public static String formatYearMonth(Date date) {
        return yearMonthFormat.get().format(date);
    }

    public static Date parseYearMonth(String str) throws ParseException {
        return yearMonthFormat.get().parse(str);
    }

    public static Date parseDateTime(String str) throws ParseException {
        return dateTimeFormat.get().parse(str);
    }

    public static Date parseYYYY_MM(String str) throws ParseException {
        return YYYY_MMFormat.get().parse(str);
    }

    public static String parseYYYY_YEAR_MM_MONTH_Format(Date date) throws ParseException {
        return YYYY_YEAR_MM_MONTH_Format.get().format(date);
    }

    public static String formatDatetime(Date date) {
        return dateTimeFormat.get().format(date);
    }

    public static String formatTime(Date date) {
        return timeFormat.get().format(date);
    }

    public static Date parseMillisecond(String str) throws ParseException {
        return millisecondFormat.get().parse(str);
    }

    public static String formatMillisecond(Date date) {
        return millisecondFormat.get().format(date);
    }


    public static String currentTimeSeconds() {
        String seconds = String.valueOf(System.currentTimeMillis());
        seconds = seconds.substring(0, seconds.length() - 3);
        return seconds;
    }
    /**
     * 1大于-1小于
     * @param s
     * @param e
     * @return
     * @throws ParseException
     */
    public static int compareDate(String s, String e) throws ParseException {
        if(parseYYYY_MM(s)==null||parseYYYY_MM(e)==null){
            return -1;
        }
        return parseYYYY_MM(s).before(parseYYYY_MM(e)) ? 1 : -1;
    }

}

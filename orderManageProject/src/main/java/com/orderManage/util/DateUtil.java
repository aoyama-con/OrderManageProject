package com.orderManage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;


/**
 * DateUtilクラス
 * 
 * @author 
 * @version
 */
public class DateUtil {

	/**
	 * 
	 * @return
	 */
	public static String getSysDateYyyyMmDd() {	// TODO メソッド名
        Date d = new Date();

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String dat = fmt.format(d);
        
        return dat;
	}
	
	/**
     * <p>文字列をyyyy/MM/dd形式の日付に変換して返します</p>
     * 
	 * @param str 文字列
	 * @return 日付
	 * @throws ParseException
	 */
	public static Date parseDate(String str) throws ParseException {
		return DateUtils.parseDate(str, "yyyy/MM/dd");
	}

	/**
     * <p>文字列を書式に合わせて日付に変換して返します</p>
     * 
	 * @param str 文字列
	 * @param pattern 書式
	 * @return 日付
	 * @throws ParseException
	 */
	public static Date ParseDate(String str, String pattern) throws ParseException {
		return DateUtils.parseDate(str, pattern);
	}

	/**
     * <p>日付に日数を加算して返します</p>
     * 
	 * @param date 日付
	 * @param day 日数
	 * @return 日付
	 * @throws ParseException
	 */
	public static Date addDays(Date date, int day) {
		return DateUtils.addDays(date, day);
	}
	
	/**
     * <p>日付に月数を加算して返します</p>
     * 
	 * @param date 日付
	 * @param month 月数
	 * @return 日付
	 * @throws ParseException
	 */
	public static Date addMonths(Date date, int month) {
		return DateUtils.addMonths(date, month);
	}
	
	/**
     * <p>日付に年数を加算して返します</p>
     * 
	 * @param date 日付
	 * @param year 年数
	 * @return 日付
	 * @throws ParseException
	 */
	public static Date addYear(Date date, int year) {
		return DateUtils.addMonths(date, year);
	}
	
	/**
     * <p>同日かを返します</p>
     * 
	 * @param date1 日付1
	 * @param date2 日付2
	 * @return 同日の場合、{@code true}を返します
	 * @throws ParseException
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		return DateUtils.isSameDay(date1, date2);
	}

	/**
     * <p>同日かを返します</p>
     * 
	 * @param cal1 日付1
	 * @param cal2 日付2
	 * @return 同日の場合、{@code true}を返します
	 * @throws ParseException
	 */
	public static boolean isSameDate(Calendar cal1, Calendar cal2) {
		return DateUtils.isSameDay(cal1, cal2);
	}

	/**
     * <p>月初を返します</p>
     * 
	 * @param date 日付
	 * @return 月初
	 * @throws ParseException
	 */
	public static Date truncate(Date date) {
		return DateUtils.truncate(date, Calendar.MONTH);
	}
}

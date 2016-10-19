package com.moka.framework.util;


import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DateUtil {

	private static final String dateFormat_yyyyMMdd = "yyyyMMdd";
	private static final String dateFormat_yyyyMM = "yyyyMM";

	private static final SimpleDateFormat dateFormat_getDiffDayCount = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
	private static final SimpleDateFormat dateFormat_serverTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

	/**
	 * Today & Current Date / Time / Hour / Month / DayOfWeek .. etc
	 */

	public static Date getTodayDate() {
		return Calendar.getInstance().getTime();
	}

	public static int getCurrentWeekOfDay() { // return 1 ~ 7 ( 일요일 월 화 수 목 금 토요일 )
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

	public static int getTomorrowWeekOfDay() { // return 1 ~ 7 ( 일요일 월 화 수 목 금 토요일 )
		Calendar calendar = Calendar.getInstance();
		calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static String getTomorrowWeekOfDayString() { // return 1 ~ 7 ( 일요일 월 화 수 목 금 토요일 )
		Calendar calendar = Calendar.getInstance();
		calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
		switch ( calendar.get(Calendar.DAY_OF_WEEK) ) {
			case Calendar.SUNDAY:
				return "일요일";
			case Calendar.MONDAY:
				return "월요일";
			case Calendar.TUESDAY:
				return "화요일";
			case Calendar.WEDNESDAY:
				return "수요일";
			case Calendar.THURSDAY:
				return "목요일";
			case Calendar.FRIDAY:
				return "금요일";
			default:
				return "토요일";
		}
	}

	public static int getTodayInt_yyyyMMdd() {
		return formatDateToInt_yyyyMMdd(getTodayDate());
	}

	public static String getTodayString_yyyyMMdd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat_yyyyMMdd, Locale.getDefault());
		return dateFormat.format(date);
	}

	public static String getTodayString_format(String format) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
		return dateFormat.format(date);
	}

	public static String getCurrentTimeString() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		return String.format("%02d:%02d", hour, minute);
	}

	public static int getCurrentHour() { // 24시간제
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Get Month // Jan: 0 ~ Dec: 11 ( +1 해서 return )
	 */

	public static int getCurrentMonth_yyyyMM() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat_yyyyMM, Locale.getDefault());
		return Integer.parseInt(dateFormat.format(getTodayDate()));
	}

	public static int getMonthFromDate_yyyyMM(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat_yyyyMM, Locale.getDefault());
		return Integer.parseInt(dateFormat.format(date));
	}

	public static Date getMonthDiff(Date date, int diff) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, diff);
		return calendar.getTime();
	}

	/** **/


	/**
	 * Format
	 */

	public static String formatDateToString(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
		return dateFormat.format(date);
	}

	public static String formatTimestampToString(long timestamp, String format) {
		return formatDateToString(timestampToDate(timestamp), format);
	}

	public static int formatDateToInt_yyyyMMdd(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat_yyyyMMdd, Locale.getDefault());
		return Integer.parseInt(dateFormat.format(date));
	}

	public static int formatDateToInt_yyyyMM(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat_yyyyMM, Locale.getDefault());
		return Integer.parseInt(dateFormat.format(date));
	}

	public static String formatStringToString(String zTime) {
		SimpleDateFormat dateFormat_to = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
		SimpleDateFormat dateFormat_from = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
		try {
			return dateFormat_to.format(dateFormat_from.parse(zTime));
		}
		catch ( ParseException e ) {
			return "";
		}
	}

	public static String formatIntDateToString_yyyyMMdd(int date_yyyyMMdd) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM월 dd일", Locale.getDefault());
		return dateFormat.format(parseDate(date_yyyyMMdd));
	}

	public static String formatIntDateToString_format(int date_yyyyMMdd, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
		return dateFormat.format(parseDate(date_yyyyMMdd));
	}

	/** **/

	/**
	 * Parse Date ( should return Date )
	 */

	public static Date parseDate(String date_yyyyMMdd) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
			return dateFormat.parse(date_yyyyMMdd);
		}
		catch ( ParseException parseException ) {
			return new Date();
		}
	}

	public static Date parseDate(int dateTime_yyyyMMdd) {
		return parseDate(String.valueOf(dateTime_yyyyMMdd));
	}

	public static Date parseDate(long dateTime_yyyyMMdd) {
		return parseDate(String.valueOf(dateTime_yyyyMMdd));
	}

	public static Date parseTodayDate(int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		return cal.getTime();
	}

	public static Date parseTomorrowDate(int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.add(GregorianCalendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		return cal.getTime();
	}

	/**
	 */

	private static String getDiffTimeStringFromMinutes(int diffMinutes) {
		int diffHours = diffMinutes / 60;

		if ( 48 < diffHours ) {

			if ( 30 < diffMinutes / 60 / 24 )
				return diffMinutes / 60 / 24 / 30 + "달전";
			else
				return diffMinutes / 60 / 24 + "일전";
		}
		else if ( 23 < diffHours && 48 > diffHours )
			return "하루전";
		else if ( 1 <= diffHours )
			return diffHours + "시간 전";
		else if ( 1 < diffMinutes )
			return diffMinutes + "분 전";
		else
			return "방금 전";
	}

	@NotNull
	public static String getDiffTimeString(long createdAt) {
		Date time = timestampToDate(createdAt);
		int diffMinutes = getDiffTimeMinutes(time, new Date());

		return getDiffTimeStringFromMinutes(diffMinutes);
	}

	/** **/

	/**
	 * about TimeStamp
	 */

	public static long getTimestampInSecond() {
		return System.currentTimeMillis() / 1000;
	}

	public static long getTimestampInMillis() {
		return System.currentTimeMillis();
	}

	private static final Calendar calendar_timestampToCalendar = Calendar.getInstance();

	public static Calendar timestampToCalendar(long timestamp) {
		synchronized ( calendar_timestampToCalendar ) {

			calendar_timestampToCalendar.setTimeInMillis(timestamp * 1000);
			return calendar_timestampToCalendar;
		}
	}

	private static final Calendar calendar_getTimestampFromDate = Calendar.getInstance();

	public static long getTimestampFromDate(Date date) {
		synchronized ( calendar_getTimestampFromDate ) {

			calendar_getTimestampFromDate.setTime(date);
			return calendar_getTimestampFromDate.getTimeInMillis();
		}
	}

	public static Date timestampToDate(long timestamp) {
		return timestampToCalendar(timestamp).getTime();
	}

	/** **/

	/**
	 * intDate , diff intDate
	 */

	private static final Calendar calendar_getDateFrom = Calendar.getInstance();

	public static Date getDateFrom(Date date, int diff) {
		synchronized ( calendar_getDateFrom ) {
			calendar_getDateFrom.setTime(date);
			calendar_getDateFrom.add(GregorianCalendar.DAY_OF_YEAR, diff);

			return calendar_getDateFrom.getTime();
		}
	}

	public static Date getDateFrom(int date, int diff) {
		return getDateFrom(parseDate(date), diff);
	}

	public static int getIntDateFrom(int date, int diff) {
		return formatDateToInt_yyyyMMdd(getDateFrom(date, diff));
	}

	private static final Calendar calendar_getDiffDateFromToday = Calendar.getInstance();

	public static Date getDiffDateFromToday(int diff) {
		Date todayDate = getTodayDate();

		synchronized ( calendar_getDiffDateFromToday ) {
			calendar_getDiffDateFromToday.setTime(todayDate);
			calendar_getDiffDateFromToday.add(GregorianCalendar.DAY_OF_YEAR, diff);

			return calendar_getDiffDateFromToday.getTime();
		}
	}

	public static int getIntDiffDateFromToday(int diff) {
		return formatDateToInt_yyyyMMdd(getDiffDateFromToday(diff));
	}

	public static double getDiffMinuteEachTimestamp(long preTimestamp, long timestamp) {
		return Math.floor(( timestamp - preTimestamp ) / 60);
	}

	public static int getDiffDayCount(String fromDate, String toDate) {
		try {
			synchronized ( dateFormat_getDiffDayCount ) {
				return getDiffDayCount(dateFormat_getDiffDayCount.parse(fromDate), dateFormat_getDiffDayCount.parse(toDate));
			}
		}
		catch ( ParseException parseException ) {

			return 0;
		}
	}

	public static int getDiffDayCount(int fromDate, int toDate) {
		return getDiffDayCount(String.valueOf(fromDate), String.valueOf(toDate));
	}

	public static int getDiffDayCount(Date fromDate, Date toDate) {
		int fromDateInt = formatDateToInt_yyyyMMdd(fromDate);
		int toDateInt = formatDateToInt_yyyyMMdd(toDate);

		Date fromDateNew = parseDate(fromDateInt);
		Date toDateNew = parseDate(toDateInt);

		return (int) ( ( toDateNew.getTime() - fromDateNew.getTime() ) / ( 24 * 60 * 60 * 1000 ) );
	}

	public static int getDiffTimeMinutes(Date fromDate, Date toDate) {
		return (int) ( ( toDate.getTime() - fromDate.getTime() ) / ( 60 * 1000 ) );
	}

}

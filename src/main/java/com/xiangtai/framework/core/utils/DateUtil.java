package com.xiangtai.framework.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public class DateUtil {
	   // 各种时间格式
		public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat(
				"yyyy-MM-dd");
		// 各种时间格式
		public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
				"yyyyMMdd");
		public static final String YYYYMMDD="yyyyMMdd";
		public static final String YYYY_MM_DD="yyyy-MM-dd";
		// 各种时间格式
		public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat(
				"yyyy年MM月dd日");
		public static final SimpleDateFormat time_sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat(
		"yyyyMMddHHmmss");
		public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat(
				"HH:mm");
		public static final  SimpleDateFormat datetimeFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
		public static final  String datetimeFormatStr = "yyyy-MM-dd HH:mm:ss";
		
		// 以毫秒表示的时间
		private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
		private static final long HOUR_IN_MILLIS = 3600 * 1000;
		private static final long MINUTE_IN_MILLIS = 60 * 1000;
		private static final long SECOND_IN_MILLIS = 1000;
		
		// 指定模式的时间格式
		private static SimpleDateFormat getSDFormat(String pattern) {
			return new SimpleDateFormat(pattern);
		}

		/**
		 * 当前日历，这里用中国时间表示
		 * 
		 * @return 以当地时区表示的系统当前日历
		 */
		public static Calendar getCalendar() {
			return Calendar.getInstance();
		}

		/**
		 * 指定毫秒数表示的日历
		 * 
		 * @param millis
		 *            毫秒数
		 * @return 指定毫秒数表示的日历
		 */
		public static Calendar getCalendar(long millis) {
			Calendar cal = Calendar.getInstance();
			// --------------------cal.setTimeInMillis(millis);
			cal.setTime(new Date(millis));
			return cal;
		}

		// ////////////////////////////////////////////////////////////////////////////
		// getDate
		// 各种方式获取的Date
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * 当前日期
		 * 
		 * @return 系统当前时间
		 */
		public static Date getDate() {
			return new Date();
		}

		/**
		 * 指定毫秒数表示的日期
		 * 
		 * @param millis
		 *            毫秒数
		 * @return 指定毫秒数表示的日期
		 */
		public static Date getDate(long millis) {
			return new Date(millis);
		}

		/**
		 * 时间戳转换为字符串
		 * 
		 * @param time
		 * @return
		 */
		public static String timestamptoStr(Timestamp time) {
			Date date = null;
			if (null != time) {
				date = new Date(time.getTime());
			}
			return date2Str(yyyy_MM_dd);
		}

		/**
		 * 字符串转换时间戳
		 * 
		 * @param str
		 * @return
		 */
		public static Timestamp str2Timestamp(String str) {
			Date date = str2Date(str, yyyy_MM_dd);
			return new Timestamp(date.getTime());
		}

		public static Date str2Date(String str, SimpleDateFormat sdf) {
			if (null == str || "".equals(str)) {
				return null;
			}
			Date date = null;
			try {
				date = sdf.parse(str);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 日期转换为字符串
		 * 
		 * @param date
		 *            日期
		 * @param format
		 *            日期格式
		 * @return 字符串
		 */
		public static String date2Str(SimpleDateFormat date_sdf) {
			Date date=getDate();
			if (null == date) {
				return null;
			}
			return date_sdf.format(date);
		}
		/**
		 * 格式化时间
		 * @param data
		 * @param format
		 * @return
		 */
		public static String dataformat(String data,String format)
		{
			SimpleDateFormat sformat = new SimpleDateFormat(format);
			Date date=null;
			try {
				 date=sformat.parse(data);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sformat.format(date);
		}
		/**
		 * 日期转换为字符串
		 * 
		 * @param date
		 *            日期
		 * @param format
		 *            日期格式
		 * @return 字符串
		 */
		public static String date2Str(Date date, SimpleDateFormat date_sdf) {
			if (null == date) {
				return null;
			}
			return date_sdf.format(date);
		}
		/**
		 * 日期转换为字符串
		 * 
		 * @param date
		 *            日期
		 * @param format
		 *            日期格式
		 * @return 字符串
		 */
		public static String getDate(String format) {
			Date date=new Date();
			if (null == date) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}

		/**
		 * 指定毫秒数的时间戳
		 * 
		 * @param millis
		 *            毫秒数
		 * @return 指定毫秒数的时间戳
		 */
		public static Timestamp getTimestamp(long millis) {
			return new Timestamp(millis);
		}

		/**
		 * 以字符形式表示的时间戳
		 * 
		 * @param time
		 *            毫秒数
		 * @return 以字符形式表示的时间戳
		 */
		public static Timestamp getTimestamp(String time) {
			return new Timestamp(Long.parseLong(time));
		}

		/**
		 * 系统当前的时间戳
		 * 
		 * @return 系统当前的时间戳
		 */
		public static Timestamp getTimestamp() {
			return new Timestamp(new Date().getTime());
		}

		/**
		 * 指定日期的时间戳
		 * 
		 * @param date
		 *            指定日期
		 * @return 指定日期的时间戳
		 */
		public static Timestamp getTimestamp(Date date) {
			return new Timestamp(date.getTime());
		}

		/**
		 * 指定日历的时间戳
		 * 
		 * @param cal
		 *            指定日历
		 * @return 指定日历的时间戳
		 */
		public static Timestamp getCalendarTimestamp(Calendar cal) {
			// ---------------------return new Timestamp(cal.getTimeInMillis());
			return new Timestamp(cal.getTime().getTime());
		}

		public static Timestamp gettimestamp() {
			Date dt = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = df.format(dt);
			java.sql.Timestamp buydate = java.sql.Timestamp.valueOf(nowTime);
			return buydate;
		}

		// ////////////////////////////////////////////////////////////////////////////
		// getMillis
		// 各种方式获取的Millis
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * 系统时间的毫秒数
		 * 
		 * @return 系统时间的毫秒数
		 */
		public static long getMillis() {
			return new Date().getTime();
		}

		/**
		 * 指定日历的毫秒数
		 * 
		 * @param cal
		 *            指定日历
		 * @return 指定日历的毫秒数
		 */
		public static long getMillis(Calendar cal) {
			// --------------------return cal.getTimeInMillis();
			return cal.getTime().getTime();
		}

		/**
		 * 指定日期的毫秒数
		 * 
		 * @param date
		 *            指定日期
		 * @return 指定日期的毫秒数
		 */
		public static long getMillis(Date date) {
			return date.getTime();
		}

		/**
		 * 指定时间戳的毫秒数
		 * 
		 * @param ts
		 *            指定时间戳
		 * @return 指定时间戳的毫秒数
		 */
		public static long getMillis(Timestamp ts) {
			return ts.getTime();
		}

		// ////////////////////////////////////////////////////////////////////////////
		// formatDate
		// 将日期按照一定的格式转化为字符串
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * 默认方式表示的系统当前日期，具体格式：年-月-日
		 * 
		 * @return 默认日期按“年-月-日“格式显示
		 */
		public static String formatDate() {
			return yyyy_MM_dd.format(getCalendar().getTime());
		}
		/**
		 * 获取时间字符串
		 */
		public static String getDataString(SimpleDateFormat formatstr) {
			return formatstr.format(getCalendar().getTime());
		}
		/**
		 * 指定日期的默认显示，具体格式：年-月-日
		 * 
		 * @param cal
		 *            指定的日期
		 * @return 指定日期按“年-月-日“格式显示
		 */
		public static String formatDate(Calendar cal) {
			return yyyy_MM_dd.format(cal.getTime());
		}

		/**
		 * 指定日期的默认显示，具体格式：年-月-日
		 * 
		 * @param date
		 *            指定的日期
		 * @return 指定日期按“年-月-日“格式显示
		 */
		public static String formatDate(Date date) {
			return yyyy_MM_dd.format(date);
		}

		/**
		 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日
		 * 
		 * @param millis
		 *            指定的毫秒数
		 * @return 指定毫秒数表示日期按“年-月-日“格式显示
		 */
		public static String formatDate(long millis) {
			return yyyy_MM_dd.format(new Date(millis));
		}

		/**
		 * 默认日期按指定格式显示
		 * 
		 * @param pattern
		 *            指定的格式
		 * @return 默认日期按指定格式显示
		 */
		public static String formatDate(String pattern) {
			return getSDFormat(pattern).format(getCalendar().getTime());
		}

		/**
		 * 指定日期按指定格式显示
		 * 
		 * @param cal
		 *            指定的日期
		 * @param pattern
		 *            指定的格式
		 * @return 指定日期按指定格式显示
		 */
		public static String formatDate(Calendar cal, String pattern) {
			return getSDFormat(pattern).format(cal.getTime());
		}

		

		// ////////////////////////////////////////////////////////////////////////////
		// formatTime
		// 将日期按照一定的格式转化为字符串
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * 默认方式表示的系统当前日期，具体格式：年-月-日 时：分
		 * 
		 * @return 默认日期按“年-月-日 时：分“格式显示
		 */
		public static String formatTime() {
			return time_sdf.format(getCalendar().getTime());
		}

		/**
		 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日 时：分
		 * 
		 * @param millis
		 *            指定的毫秒数
		 * @return 指定毫秒数表示日期按“年-月-日 时：分“格式显示
		 */
		public static String formatTime(long millis) {
			return time_sdf.format(new Date(millis));
		}

		/**
		 * 指定日期的默认显示，具体格式：年-月-日 时：分
		 * 
		 * @param cal
		 *            指定的日期
		 * @return 指定日期按“年-月-日 时：分“格式显示
		 */
		public static String formatTime(Calendar cal) {
			return time_sdf.format(cal.getTime());
		}

		/**
		 * 指定日期的默认显示，具体格式：年-月-日 时：分
		 * 
		 * @param date
		 *            指定的日期
		 * @return 指定日期按“年-月-日 时：分“格式显示
		 */
		public static String formatTime(Date date) {
			return time_sdf.format(date);
		}

		// ////////////////////////////////////////////////////////////////////////////
		// formatShortTime
		// 将日期按照一定的格式转化为字符串
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * 默认方式表示的系统当前日期，具体格式：时：分
		 * 
		 * @return 默认日期按“时：分“格式显示
		 */
		public static String formatShortTime() {
			return short_time_sdf.format(getCalendar().getTime());
		}

		/**
		 * 指定毫秒数表示日期的默认显示，具体格式：时：分
		 * 
		 * @param millis
		 *            指定的毫秒数
		 * @return 指定毫秒数表示日期按“时：分“格式显示
		 */
		public static String formatShortTime(long millis) {
			return short_time_sdf.format(new Date(millis));
		}

		/**
		 * 指定日期的默认显示，具体格式：时：分
		 * 
		 * @param cal
		 *            指定的日期
		 * @return 指定日期按“时：分“格式显示
		 */
		public static String formatShortTime(Calendar cal) {
			return short_time_sdf.format(cal.getTime());
		}

		/**
		 * 指定日期的默认显示，具体格式：时：分
		 * 
		 * @param date
		 *            指定的日期
		 * @return 指定日期按“时：分“格式显示
		 */
		public static String formatShortTime(Date date) {
			return short_time_sdf.format(date);
		}

		// ////////////////////////////////////////////////////////////////////////////
		// parseDate
		// parseCalendar
		// parseTimestamp
		// 将字符串按照一定的格式转化为日期或时间
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
		 * 
		 * @param src
		 *            将要转换的原始字符窜
		 * @param pattern
		 *            转换的匹配格式
		 * @return 如果转换成功则返回转换后的日期
		 * @throws ParseException
		 * @throws AIDateFormatException
		 */
		public static Date parseDate(String src, String pattern)
				throws ParseException {
			return getSDFormat(pattern).parse(src);

		}

		/**
		 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
		 * 
		 * @param src
		 *            将要转换的原始字符窜
		 * @param pattern
		 *            转换的匹配格式
		 * @return 如果转换成功则返回转换后的日期
		 * @throws ParseException
		 * @throws AIDateFormatException
		 */
		public static Calendar parseCalendar(String src, String pattern)
				throws ParseException {

			Date date = parseDate(src, pattern);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		}

		public static String formatAddDate(String src, String pattern, int amount)
				throws ParseException {
			Calendar cal;
			cal = parseCalendar(src, pattern);
			cal.add(Calendar.DATE, amount);
			return formatDate(cal);
		}

		/**
		 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
		 * 
		 * @param src
		 *            将要转换的原始字符窜
		 * @param pattern
		 *            转换的匹配格式
		 * @return 如果转换成功则返回转换后的时间戳
		 * @throws ParseException
		 * @throws AIDateFormatException
		 */
		public static Timestamp parseTimestamp(String src, String pattern)
				throws ParseException {
			Date date = parseDate(src, pattern);
			return new Timestamp(date.getTime());
		}

		// ////////////////////////////////////////////////////////////////////////////
		// dateDiff
		// 计算两个日期之间的差值
		// ////////////////////////////////////////////////////////////////////////////

		/**
		 * 计算两个时间之间的差值，根据标志的不同而不同
		 * 
		 * @param flag
		 *            计算标志，表示按照年/月/日/时/分/秒等计算
		 * @param calSrc
		 *            减数
		 * @param calDes
		 *            被减数
		 * @return 两个日期之间的差值
		 */
		public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

			long millisDiff = getMillis(calSrc) - getMillis(calDes);

			if (flag == 'y') {
				return (calSrc.get(calSrc.YEAR) - calDes.get(calDes.YEAR));
			}

			if (flag == 'd') {
				return (int) (millisDiff / DAY_IN_MILLIS);
			}

			if (flag == 'h') {
				return (int) (millisDiff / HOUR_IN_MILLIS);
			}

			if (flag == 'm') {
				return (int) (millisDiff / MINUTE_IN_MILLIS);
			}

			if (flag == 's') {
				return (int) (millisDiff / SECOND_IN_MILLIS);
			}

			return 0;
		}
	   
		
		
		/**
		 * 得到当前年
		 * @return
		 */
		public static int getYear(){
		    GregorianCalendar calendar=new GregorianCalendar();
		    calendar.setTime(getDate());
		    return calendar.get(Calendar.YEAR);
		  }
	
		public static int daysXiangJian(String dateStr1, String dateStr2) {
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  try {
			   dateStr1 = sdf.format(sdf2.parse(dateStr1));
			   dateStr2 = sdf.format(sdf2.parse(dateStr2));
			  } catch (ParseException e) {
			   e.printStackTrace();
			  }
			  int year1 = Integer.parseInt(dateStr1.substring(0, 4));
			  int month1 = Integer.parseInt(dateStr1.substring(4, 6));
			  int day1 = Integer.parseInt(dateStr1.substring(6, 8));
			  int year2 = Integer.parseInt(dateStr2.substring(0, 4));
			  int month2 = Integer.parseInt(dateStr2.substring(4, 6));
			  int day2 = Integer.parseInt(dateStr2.substring(6, 8));
			  Calendar c1 = Calendar.getInstance();
			  c1.set(Calendar.YEAR, year1);
			  c1.set(Calendar.MONTH, month1 - 1);
			  c1.set(Calendar.DAY_OF_MONTH, day1);
			  Calendar c2 = Calendar.getInstance();
			  c2.set(Calendar.YEAR, year2);
			  c2.set(Calendar.MONTH, month2 - 1);
			  c2.set(Calendar.DAY_OF_MONTH, day2);
			  long mills =
			   c1.getTimeInMillis() > c2.getTimeInMillis()
			    ? c1.getTimeInMillis() - c2.getTimeInMillis()
			    : c2.getTimeInMillis() - c1.getTimeInMillis();
			  return (int) (mills / 1000 / 3600 / 24);
		 }
		public static String CDate (){
			 Calendar ca = Calendar.getInstance(); 
		      int year = ca.get(Calendar.YEAR);//获取年份 
		      int month=ca.get(Calendar.MONTH)+1;//获取月份 
		      int day=ca.get(Calendar.DATE);//获取日 
		      int minute=ca.get(Calendar.MINUTE);//分 
		      int hour=ca.get(Calendar.HOUR);//小时 
		      int second=ca.get(Calendar.SECOND);//秒 
		      int WeekOfYear = ca.get(Calendar.DAY_OF_WEEK); 
		     // System.out.println("用Calendar.getInstance().getTime()方式显示时间: " + ca.getTime()); 
		      return year +"年"+ month +"月"+ day + "日"; 
		}
		public static void main(String[] args) {
			int n = daysXiangJian("2014-08-07 14:42:00", "2014-08-06 14:43:00");
			System.out.println(n);
			
		}
		
}


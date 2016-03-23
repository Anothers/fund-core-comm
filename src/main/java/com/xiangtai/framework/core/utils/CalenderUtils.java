package com.xiangtai.framework.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalenderUtils {
	 /** 
	   * 方法说明：日期字段串 ，月份+1
	   * 创建者：范兴乾
	   * 返回类型：String
	   * 创建时间：2014-10-15 上午11:53:24 
	   * 参数列表： 
	   */ 
	public static String strToCalendarAdd(String dateStr, String fmtStr) {
		Date date = parseDate(dateStr, fmtStr);
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.setTime(date);
		//验证是否为月末
		cal2.setTime(date);
		cal2.add(cal2.MONTH, 1);
		cal2.set(Calendar.DAY_OF_MONTH, 0);
		String lastDate = cal2.get(Calendar.YEAR) 
				+ IOUtils.leftPad(String.valueOf(cal2.get(Calendar.MONTH)+1), '0', 2)
				+IOUtils.leftPad(String.valueOf(cal2.get(Calendar.DATE)), '0', 2);
		cal.add(cal.MONTH, 1);
		if(dateStr.equals(lastDate)) {
			//日期为月末
			int lastDays = cal.getActualMaximum(Calendar.DATE);
			cal.set(Calendar.DAY_OF_MONTH, lastDays);
		}
		//非月末
		String newDt = cal.get(Calendar.YEAR) 
				+ IOUtils.leftPad(String.valueOf(cal.get(Calendar.MONTH)+1), '0', 2)
				+IOUtils.leftPad(String.valueOf(cal.get(Calendar.DATE)), '0', 2);
		return newDt;
	}
	
	 /** 
	   * 方法说明：日期字段串 ，月份-1
	   * 创建者：范兴乾
	   * 返回类型：String
	   * 创建时间：2014-10-16 上午11:39:19 
	   * 参数列表： 
	   */ 
	public static String strToCalendarSubtract(String dateStr, String fmtStr) {
		Date date = parseDate(dateStr, fmtStr);
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.setTime(date);
		//验证是否为月末
		cal2.setTime(date);
		cal2.set(Calendar.DAY_OF_MONTH, 0);
		String lastDate = cal2.get(Calendar.YEAR) 
				+ IOUtils.leftPad(String.valueOf(cal2.get(Calendar.MONTH)+1), '0', 2)
				+IOUtils.leftPad(String.valueOf(cal2.get(Calendar.DATE)), '0', 2);
		cal.add(cal.MONTH, -1);
		if(dateStr.equals(lastDate)) {
			//日期为月末
			int lastDays = cal.getActualMaximum(Calendar.DATE);
			cal.set(Calendar.DAY_OF_MONTH, lastDays);
		}
		//非月末
		String newDt = cal.get(Calendar.YEAR) 
				+ IOUtils.leftPad(String.valueOf(cal.get(Calendar.MONTH)+1), '0', 2)
				+IOUtils.leftPad(String.valueOf(cal.get(Calendar.DATE)), '0', 2);
		return newDt;
	}
	
	 /** 
	   * 方法说明：根据周期类型-得到当前周期开始日期
	   * 创建者：范兴乾
	   * 返回类型：String
	   * 创建时间：2014-10-27 下午1:48:57 
	   * 参数列表： 
	   */ 
	public static String strToCycleStart(String startDt, String dateStr, String cycleFlag, String fmtStr) {
		String startDtOld = startDt;
		String cycleStartDate = "";
		//周期为月
		if("M".equals(cycleFlag)) {
			while(true) {
				if(startDt.substring(0, 6).equals(dateStr.substring(0, 6))) {
					if(startDt.compareTo(dateStr) > 0) {
						cycleStartDate = strToCalendarSubtract(startDt, fmtStr);
					} else {
						cycleStartDate = startDt;
					}
					break;
				}
				startDt = strToCalendarAdd(startDt, fmtStr);
			}
		} 
		//季
		else if("Q".equals(cycleFlag)) {
			//第一季(下个周期开始日期)
			String startDtTmp = strToCalendarAdd(startDt, fmtStr);
			for(int i=0;i<2;i++) {
				startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
			}
			while(true) {
				if(startDt.compareTo(dateStr) <= 0 && dateStr.compareTo(startDtTmp) < 0) {
					cycleStartDate = startDt;
					break;
				}
				startDt = startDtTmp;
				for(int i=0;i<3;i++) {
					startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
				}
			}
		}
		//半年
		else if("S".equals(cycleFlag)) {
			//第一年(下个周期开始日期)
			String startDtTmp = strToCalendarAdd(startDt, fmtStr);
			for(int i=0;i<5;i++) {
				startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
			}
			while(true) {
				if(startDt.compareTo(dateStr) <= 0 && dateStr.compareTo(startDtTmp) < 0) {
					cycleStartDate = startDt;
					break;
				}
				startDt = startDtTmp;
				for(int i=0;i<6;i++) {
					startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
				}
			}
		} 
		//年
		else if("Y".equals(cycleFlag)) {
			//第一年(下个周期开始日期)
			String startDtTmp = strToCalendarAdd(startDt, fmtStr);
			for(int i=0;i<11;i++) {
				startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
			}
			while(true) {
				if(startDt.compareTo(dateStr) <= 0 && dateStr.compareTo(startDtTmp) < 0) {
					cycleStartDate = startDt;
					break;
				}
				startDt = startDtTmp;
				for(int i=0;i<12;i++) {
					startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
				}
			}
		} 
		//判断日期是否匹配
		Date date = parseDate(cycleStartDate, fmtStr);
		Date date1 = parseDate(startDtOld, fmtStr);
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.setTime(date);
		cal1.setTime(date1);
		int days = cal.getActualMaximum(Calendar.DATE);
		int days1 = cal1.getActualMaximum(Calendar.DATE);
		int startDtDays = Integer.parseInt(startDtOld.substring(6));
		if(startDtDays != days1 && startDtDays < days) {
			cycleStartDate = cycleStartDate.substring(0, 6) + startDtOld.substring(6);
		}
		return cycleStartDate;
	}
	
	 /** 
	   * 方法说明：根据周期类型-得到下个周期开始日期
	   * 创建者：范兴乾
	   * 返回类型：String
	   * 创建时间：2014-10-27 下午12:22:49 
	   * 参数列表： 
	   */ 
	public static String strToCycleEnd(String startDt, String dateStr, String cycleFlag, String fmtStr) {
		String startDtOld = startDt;
		String cycleEndDate = "";
		//周期为月
		if("M".equals(cycleFlag)) {
			while(true) {
				if(startDt.substring(0, 6).equals(dateStr.substring(0, 6))) {
					if(startDt.compareTo(dateStr) > 0) {
						cycleEndDate = startDt;
					}  else {
						cycleEndDate = strToCalendarAdd(startDt, fmtStr);
					}
					break;
				}
				startDt = strToCalendarAdd(startDt, fmtStr);
			}
		} 
		//季
		else if("Q".equals(cycleFlag)) {
			//第一季(下个周期开始日期)
			String startDtTmp = strToCalendarAdd(startDt, fmtStr);
			for(int i=0;i<2;i++) {
				startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
			}
			while(true) {
				if(startDt.compareTo(dateStr) <= 0 && dateStr.compareTo(startDtTmp) < 0) {
					cycleEndDate = startDtTmp;
					break;
				}
				startDt = startDtTmp;
				for(int i=0;i<3;i++) {
					startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
				}
			}
		}
		//半年
		else if("S".equals(cycleFlag)) {
			//第一年(下个周期开始日期)
			String startDtTmp = strToCalendarAdd(startDt, fmtStr);
			for(int i=0;i<5;i++) {
				startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
			}
			while(true) {
				if(startDt.compareTo(dateStr) <= 0 && dateStr.compareTo(startDtTmp) < 0) {
					cycleEndDate = startDtTmp;
					break;
				}
				startDt = startDtTmp;
				for(int i=0;i<6;i++) {
					startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
				}
			}
		} 
		//年
		else if("Y".equals(cycleFlag)) {
			//第一年(下个周期开始日期)
			String startDtTmp = strToCalendarAdd(startDt, fmtStr);
			for(int i=0;i<11;i++) {
				startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
			}
			while(true) {
				if(startDt.compareTo(dateStr) <= 0 && dateStr.compareTo(startDtTmp) < 0) {
					cycleEndDate = startDtTmp;
					break;
				}
				startDt = startDtTmp;
				for(int i=0;i<12;i++) {
					startDtTmp = strToCalendarAdd(startDtTmp, fmtStr);
				}
			}
		} 
		//判断日期是否匹配
		Date date = parseDate(cycleEndDate, fmtStr);
		Date date1 = parseDate(startDtOld, fmtStr);
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.setTime(date);
		cal1.setTime(date1);
		int days = cal.getActualMaximum(Calendar.DATE);
		int days1 = cal1.getActualMaximum(Calendar.DATE);
		int startDtDays = Integer.parseInt(startDtOld.substring(6));
		if(startDtDays != days1 && startDtDays < days) {
			cycleEndDate = cycleEndDate.substring(0, 6) + startDtOld.substring(6);
		}
		
		return cycleEndDate;
	}
	
	
	 /** 
	   * 方法说明：转换成Date类型
	   * 创建者：范兴乾
	   * 返回类型：Date
	   * 创建时间：2014-10-9 下午12:17:09 
	   * 参数列表： 
	   */ 
	public static Date parseDate(String src, String pattern) {
		try {
			return getSDFormat(pattern).parse(src);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	// 指定模式的时间格式
	private static SimpleDateFormat getSDFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}
  

    /** 
      * 方法说明：两个日期相差多少天数（time2 - time1）
      * 创建者：范兴乾
      * 返回类型：long
      * 创建时间：2014-10-9 上午11:21:08 
      * 参数列表： time1:开始日期（含） , time2:结束日期（含）
      */ 
   public static long getQuot(String time1, String time2, String sdf){
   	  long quot = 0;
   	  SimpleDateFormat ft = new SimpleDateFormat(sdf);
   	  try {
   	   Date date1 = ft.parse( time1);
   	   Date date2 = ft.parse( time2);
   	   quot = date2.getTime() - date1.getTime();
   	   quot = quot / 1000 / 60 / 60 / 24 + 1;
   	  } catch (ParseException e) {
   	   e.printStackTrace();
   	  }
   	  return quot;
   }
   public static long getQuot2(String time1, String time2, String sdf){
	   	  long quot = 0;
	   	  SimpleDateFormat ft = new SimpleDateFormat(sdf);
	   	  try {
	   	   Date date1 = ft.parse( time1);
	   	   Date date2 = ft.parse( time2);
	   	   quot = date2.getTime() - date1.getTime();
	   	   quot = quot / 1000 / 60 / 60 / 24 ;
	   	  } catch (ParseException e) {
	   	   e.printStackTrace();
	   	  }
	   	  return quot;
	   }
   /**
    * 获取当前是几号
    * @return
    */
   public static int getDate(){
	   Calendar cal = Calendar.getInstance();
	   return cal.get(Calendar.DATE);
   }
   /**
    * 获取当前月总共有多少天
    * @return
    */
   public static int getThisMonthDays(){
	   Calendar cal = Calendar.getInstance();
	   return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
   }
   /**
    * 获取当前月
    * @return
    */
   public static int getThisMonth(){
	   Calendar cal = Calendar.getInstance();
	   return cal.get(Calendar.MONTH)+1;
   }
   /** 
  * 方法说明：根据周期获取周期月数
  * 创建者：范兴乾
  * 返回类型：int
  * 创建时间：2014-11-13 下午5:36:43 
  * 参数列表： 
  */ 
  public static int getMonthNum(String interestCycle) {
		int rateCycleCount = 12;//年
		if("Q".equals(interestCycle)) { //季
			rateCycleCount = 3;
		} else if ("M".equals(interestCycle)) {
			rateCycleCount = 1;
		} else if ("S".equals(interestCycle)) { //半年
			rateCycleCount = 6;
		} 
		return rateCycleCount;
  }
 
   /**
      * 方法说明：两个日期相差的月数
      * 创建者：黄庆伟
      * 返回类型：int
      * 创建时间：2014-11-14 上午11:35:24 
      * 参数列表：
    */
   public static int getDateMonthSub(Calendar sysCalendar,Calendar calendar){
	   int result=0;
	   int sysYear=sysCalendar.get(Calendar.YEAR);
	   int sysMonth=sysCalendar.get(Calendar.MONTH);
	   int year=calendar.get(Calendar.YEAR);
	   int month=calendar.get(Calendar.MONTH);
	   if(sysYear==year){
		   result=sysMonth-month;
	   }else{
		   result=12*(sysYear-year)+  sysMonth-month;
	   }
	   return result;
   }
   /**
    * 根据签约方式和周期开始日期得到下个周期开始日期
    * @return
    */
   public static String getRenewalStartDt(String jobStartDate,String interestCycle,String jobInterestCycle){
	   	String startDt="";
	   	//系统日期
	   	Calendar sysCalendar= Calendar.getInstance();
		String sysDate=DateUtil.date2Str(sysCalendar.getTime(), DateUtil.yyyyMMdd);
		Calendar tempCalendar= Calendar.getInstance();
		//主合同开始日期
		tempCalendar.setTime(DateUtil.str2Date(jobStartDate, DateUtil.yyyyMMdd));
		startDt=DateUtil.date2Str(tempCalendar.getTime(), DateUtil.yyyyMMdd);
		while(true){
			if(startDt.compareTo(sysDate)>=0){//与系统日期比较
				break;
			}
			startDt = strToCycleEnd(startDt, startDt, jobInterestCycle, DateUtil.YYYYMMDD);
		}
		return startDt;
	}
   
   /**
    * 根据周期开始日期得到周期结束日期
    * @return
    */
    public static String getRenewalEndDt(String startDt,String interestCycle){
    	String startTemp=CalenderUtils.strToCycleEnd(startDt, startDt, interestCycle, DateUtil.YYYYMMDD);
		Calendar sysCalendar= Calendar.getInstance();
		sysCalendar.setTime(DateUtil.str2Date(startTemp, DateUtil.yyyyMMdd));
		sysCalendar.set(Calendar.DATE, sysCalendar.get(Calendar.DATE)-1);
		return  DateUtil.date2Str(sysCalendar.getTime(), DateUtil.yyyyMMdd);
    }
   /**
    * 根据周期开始日期得到上个周期的结束日期
    */
    public static String getEndDt(String startDt){
    	Calendar sysCalendar= Calendar.getInstance();
    	sysCalendar.setTime(DateUtil.str2Date(startDt, DateUtil.yyyyMMdd));
    	sysCalendar.set(Calendar.DATE, sysCalendar.get(Calendar.DATE)-1);
		return  DateUtil.date2Str(sysCalendar.getTime(), DateUtil.yyyyMMdd);
    }
     /** 
       * 方法说明：字符串日期 - 天数
       * 创建者：范兴乾
       * 返回类型：String
       * 创建时间：2014-11-15 下午3:21:56 
       * 参数列表： 
       */ 
    public static String getDateSubtractDays(String dateStr, int days) {
    	Calendar sysCalendar= Calendar.getInstance();
		sysCalendar.setTime(DateUtil.str2Date(dateStr, DateUtil.yyyyMMdd));
		sysCalendar.set(Calendar.DATE, sysCalendar.get(Calendar.DATE) - days);
		return  DateUtil.date2Str(sysCalendar.getTime(), DateUtil.yyyyMMdd);
    }
    
    /**
     * 方法说明：字符串日期 + 天数，获取截止日期
     * 创建人：范兴乾
     * 返回类型：String
     * 创建时间：2015-11-24 下午7:20:56
     */
    public static String getDateAddDays(String dateStr, int days) {
    	Calendar sysCalendar= Calendar.getInstance();
		sysCalendar.setTime(DateUtil.str2Date(dateStr, DateUtil.yyyyMMdd));
		sysCalendar.set(Calendar.DATE, sysCalendar.get(Calendar.DATE) + (days-1));
		return  DateUtil.date2Str(sysCalendar.getTime(), DateUtil.yyyyMMdd);
    }
    
     /** 
       * 方法说明：日期归属年月（利息提成）
       * 创建者：范兴乾
       * 返回类型：String
       * 创建时间：2014-12-30 下午2:39:40 
       * 参数列表： 
       */ 
    public static String getVestDt(String comCountDt) {
    	String vestDt = null;
    	String countDay = comCountDt.substring(6);
    	if(countDay.compareTo("20") <=0) {
			vestDt = comCountDt.substring(0,6);
		} else {
			String nextMonth = CalenderUtils.strToCalendarAdd(comCountDt, DateUtil.YYYYMMDD);
			vestDt = nextMonth.substring(0,6);
		}
    	return vestDt;
    }
    
    /**
     * 根据截止日期的下个周期开始日期
     * @return
     */
     public static String getRenewalStartDt(String endDt){
 		Calendar sysCalendar= Calendar.getInstance();
 		sysCalendar.setTime(DateUtil.str2Date(endDt, DateUtil.yyyyMMdd));
 		sysCalendar.set(Calendar.DATE, sysCalendar.get(Calendar.DATE)+1);
 		return  DateUtil.date2Str(sysCalendar.getTime(), DateUtil.yyyyMMdd);
     }
     
     public static String getRenewalStartDt1(String startDate,String renewalInterestCycle){
 	   	String startDt="";
 	   	//系统日期
 	   	Calendar sysCalendar= Calendar.getInstance();
 		String sysDate=DateUtil.date2Str(sysCalendar.getTime(), DateUtil.yyyyMMdd);
 		Calendar tempCalendar= Calendar.getInstance();
 		tempCalendar.setTime(DateUtil.str2Date(startDate, DateUtil.yyyyMMdd));
 		startDt=DateUtil.date2Str(tempCalendar.getTime(), DateUtil.yyyyMMdd);
 		String endDt=CalenderUtils.getRenewalEndDt(startDt, renewalInterestCycle);
 		while(true){
 			if(endDt.compareTo(sysDate)>=0){//与系统日期比较
 				break;
 			}
 			startDt = strToCycleEnd(startDt, startDt, renewalInterestCycle, DateUtil.YYYYMMDD);
 			endDt=CalenderUtils.getRenewalEndDt(startDt, renewalInterestCycle);
 		}
 		return startDt;
 	}
     
    /**
     * 方法说明：当前时间+分钟
     * 创建人：范兴乾
     * 返回类型：String
     * 创建时间：2015-6-26 下午8:21:07
     */
    public static String getCurrentAddMinute(int minute) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	 Calendar nowTime = Calendar.getInstance();
    	  nowTime.add(Calendar.MINUTE, minute);
    	  String nowTimeStr = sdf.format(nowTime.getTime());
    	  return nowTimeStr+":00";
    }
     
	public static void main(String[] args) {
		String nowTimeStr = getDateAddDays("20151123", 100);
		System.out.println(nowTimeStr);
	}
	
}

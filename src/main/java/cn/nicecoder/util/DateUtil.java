package cn.nicecoder.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

	/**
	 * 转换时间格式"XXXX-XX-XX"
	 */
	public static String getDate(String dateStr){
		return dateStr.substring(0,4) +"-"+ dateStr.substring(4,6) +"-"+ dateStr.substring(6,8);
	}

	/**
	 * 根据一个当前日期和需要添加的月数得到一个卡的有效期（为当前日期加上月后的日期的月的最后一天）
	 * 
	 * @param date
	 * @param monthCount
	 * @return
	 */
	public static Date countCardValidate(Date date, int monthCount) {
		Calendar maxDate = Calendar.getInstance();
		maxDate.set(Calendar.YEAR, 2099);
		maxDate.set(Calendar.MONTH, 12);
		maxDate.set(Calendar.DATE, 31);
		long maxTime = maxDate.getTimeInMillis();
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		gregorianCalendar.add(GregorianCalendar.MONDAY, monthCount+1);
		Date lastDate = gregorianCalendar.getTime();
		if(maxTime>=gregorianCalendar.getTime().getTime()){
			date = setMonthLastDay(lastDate);
		}else{
			date = setMonthLastDay(maxDate.getTime());
		}
		return date;
	}

	/**
	 * 根据一个DATE取该日期月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date setMonthLastDay(Date date) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(GregorianCalendar.DATE, 1);
		gregorianCalendar.add(GregorianCalendar.DATE, -1);
		date = gregorianCalendar.getTime();
		return date;
	}
	/**
	 * 根据传入的字符串时间格式成yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatStringDate(String date){
		if(date==null) return null;
		return date.substring(0, 4)+"-"+date.substring(4, 6)+"-"+date.substring(6, 8);
	}
	/**
	 * 根据传入的字符串时间格式成yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatStringTime(String time){
		if(time==null) return null;
		return time.substring(0, 2)+":"+time.substring(2, 4)+":"+time.substring(4, 6);
	}
	/**
	 * 根据传入的字符串时间格式成yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String StringDate(String date){
		if(date==null) return null;
		return date.substring(0, 4)+date.substring(5,7)+date.substring(8, 10);
	}
	/**
	 * 获取当前时间
	 */
	public static String getCurrentTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 获取当前时间 24
	 * @return
	 */
	public static String getCurrentTime24(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 获取当前时间 24
	 * @return
	 */
	public static String getCurrentTime24SSS(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * Date型的时间转换成String型的格式为：yyyyMMdd
	 * @return
	 */
	public static String getCurrentDateStr(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 返回指定日期格式的字符串 如：yyyyMMdd,yyyyMMddhhmmss
	 * @param format
	 * @return
	 */
	public static String getCurrentDateFormatStr(String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date());
	}
	
    /**
     * Date型的时间转换成String型的格式为：yyyy-MM-dd
     * @return
     */
	
	public static String getStringDate(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 获取当前日期
	 */
	public static Date getCurrentDate(){

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String curDate=simpleDateFormat.format(new Date());
		
		try {
			return simpleDateFormat.parse(curDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获得当前日期和时间
	 * 
	 * @return
	 */
	public static Date getCurrentDateAndTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String curDate=simpleDateFormat.format(new Date());
		
		try {
			return simpleDateFormat.parse(curDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取格式后的数据 ，返回yyyyMMdd
	 */
	public static String getFormatTime(String Date){
		if(null ==Date){
			return Date;
		}
		if(Date.equals("")){
			return "";
		}
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(Date.substring(0,4));
		strBuf.append(Date.substring(5,7));
		strBuf.append(Date.substring(8,10));
	    return strBuf.toString();
	}
	
	
	
	
	public static  String dbFormatToDateFormat(String dbFormat){
		if(dbFormat!=null&&!"".equals(dbFormat)&&dbFormat.trim().length()>=8){
			StringBuffer strBuf = new StringBuffer(dbFormat.substring(0, 4));
			strBuf.append("-");
			strBuf.append(dbFormat.substring(4, 6));
			strBuf.append("-");
			strBuf.append(dbFormat.substring(6,8));
			strBuf.append(dbFormat.substring(8));
			return strBuf.toString();
		}
		return dbFormat;
	}
	
	/**
     * Date日期转换成String
     * 
     */
    public static final String date2String(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }
    
    /***
     * Date日期转成带-String
     */
    public static final String dateToSting_(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    
    
    /**
     * String日期转换成Date
     * 
     */
    public static final Date string2date(String dateStr) {
    	if (dateStr == null || dateStr.length() == 0)
			return null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }
    /**
     * String日期转换成Date(yyyymmdd)
     * 
     */
    public static final Date string2Dateyyyymmdd(String dateStr) {
    	if (dateStr == null || dateStr.length() == 0)
			return null;
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }
    /**
     * String日期转换成Date(yyyymmddhhmmss)
     * 
     */
    public static final Date string2Dateyyyymmddhhmmss(String dateStr) {
    	if (dateStr == null || dateStr.length() == 0)
			return null;
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }  
    
	/**
     * Date日期转换成String
     * 
     */
    public static final String date2Stringyyyymmddhhmmss(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }
    
    public static final String date2StringyyyymmddhhmmssSSS(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(date);
    }
    
    /**
     * 按日加,指定日期
     * 
     * @param date
     * @param value
     * @return
     */
    public static final Date addYear(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.YEAR, value);
        return now.getTime();
    }
    
    /**
     * 按日加,指定日期
     * 
     * @param date
     * @param value
     * @return
     */
    public static final Date addDay(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DAY_OF_YEAR, value);
        return now.getTime();
    }

    /**
     * 按月加
     * 
     * @param value
     * @return
     */
    public static final Date addMonth(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
//        now.add(Calendar.MONTH, 1);
//        now.set(Calendar.DATE, value);
//        return now.getTime();
        now.add(Calendar.MONTH, value);
        return now.getTime();
    }
    
    /**
     * 按秒加
     * 
     * @param value
     * @return
     */
    public static final Date addSecond(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.SECOND, value);
        return now.getTime();
    }
    
    /**
     * 按秒加
     * yyyyMMddHHmmss
     * @param value
     * @return
     */
    public static final String addSecondyyyyMMddHHmmss(String dateStr, int value) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	Date date = null;
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.SECOND, value);
        
        return simpleDateFormat.format(now.getTime());
    }
    
    /**
     * 按秒加
     * yyyyMMddHHmmssSSS
     * @param value
     * @return
     */
    public static final String addSecondyyyyMMddHHmmssSSS(String dateStr, int value) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	Date date = null;
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.SECOND, value);
        
        return simpleDateFormat.format(now.getTime());
    }
    /**
     * 补年
     * @param dateStr
     * @return
     */
    public static final String convMMddToyyyyMMdd(String dateStr) {
    	String nowDate = getCurrentDateStr();
    	int year = Integer.parseInt(nowDate.substring(0, 4));
    	if(dateStr.substring(0,2).equals("12")&&nowDate.substring(4,6).equals("01")){
    		year--;
    	}else if(dateStr.substring(0,2).equals("01")&&nowDate.substring(4,6).equals("12")){
    		year++;
    	}
    	return year+dateStr;
    }
    /**
     * 时分秒转换成S
     * @param dateStr hhmmss
     * @return
     */
    public static final String transTimeToSecond(String dateStr) {
    	dateStr=NumberStringUtil.addLeftZero(dateStr, 6);
    	int hh=Integer.parseInt(dateStr.substring(0,2))*60*60;
    	int mm=Integer.parseInt(dateStr.substring(2,4))*60;
    	int ss=Integer.parseInt(dateStr.substring(4,6));
    	return String.valueOf(hh+mm+ss);
    }
    
    
    public static final Long compareTimeLength(String sdateStr,String edateStr,String type){
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    	   Date sdate=null;
    	   Date edate=null;
		try {
			sdate = df.parse(sdateStr);
			edate = df.parse(edateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long l=edate.getTime()-sdate.getTime();
		long count=0l;
    	  if("d".equals(type)){//得到天的差数
    		  count=l/(24*60*60*1000);
    	  }else if("h".equals(type)){//得到小时的差数
    		  count=l/(60*60*1000);
    	  }else if("m".equals(type)){//得到分钟的差数
    		  count=l/(60*1000);
    	  }else if("s".equals(type)){//得到秒的差数
    		  count=l/1000;
    	  }else{//默认得到毫秒的差数
    		  count=l;
    	  }
    	   return count;
    }
    /**
     * 得到两个日期的时间差 hhmmss
     * @param sdateStr
     * @param edateStr
     * @return
     */
    public static final String compareTimeLength(String sdateStr,String edateStr){
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
 	   Date sdate=null;
 	   Date edate=null;
		try {
			sdate = df.parse(sdateStr);
			edate = df.parse(edateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long l=edate.getTime()-sdate.getTime();
 	 
 		 long dd =l/(24*60*60*1000);
 	
 		 long hh=(l-dd*(24*60*60*1000))/(60*60*1000);
 	
 		 long mm=(l-dd*(24*60*60*1000)-hh*(60*60*1000))/(60*1000);
 	 
 		long  ss=(l-dd*(24*60*60*1000)-hh*(60*60*1000)-mm*(60*1000))/1000;
 	
 	   return (dd*24+hh)+""+mm+""+ss;
    }
    public static List<String> counttwoDays(String str1,String str2){ 
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
        Date date1=null; 
        Date date2=null; 
        List<String> datelens=new ArrayList<String>();
        try { 
          date1 = df.parse(str1); 
          date2 = df.parse(str2); 
          int s = (int) ((date2.getTime() - date1.getTime())/ (24 * 60 * 60 * 1000)); 
          if(s>=0){ 
            for(int i = 0;i<=s;i++){ 
              long todayDate = date1.getTime() + i * 24 * 60 * 60 * 1000; 
              Date tmDate = new Date(todayDate); 
              datelens.add(new SimpleDateFormat("yyyyMMdd").format(tmDate)); 
            } 
          } 
        } catch (ParseException e) { 
          System.out.println("格式错误"); 
        } 
        return datelens;
      } 
    
    
    
    /**
     * 获取当前日期是星期几
     * @param date
     * @return
     */
    public static final String getWeekOfDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd");
		try {
			Date dt= formatter.parse(date);
			 String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(dt);

		        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		        if (w < 0)
		            w = 0;
		        return weekDays[w];
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return null;

		}
       
    }

    /**
     * 获取两个日期之间的日期
     * @param
     * @return
     */
    public static final List<String> getBetweenDateDate(String startDate,String endDate) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	    List<String> result = new ArrayList<String>();
	    Calendar tempStart = Calendar.getInstance();
	    try {
			tempStart.setTime(simpleDateFormat.parse(startDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    
	    Calendar tempEnd = Calendar.getInstance();
	    try {
			tempEnd.setTime(simpleDateFormat.parse(endDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    while (tempStart.before(tempEnd)) {
	        result.add(simpleDateFormat.format(tempStart.getTime()));
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    result.add(endDate);//最后一天也加上去
	    return result;
    }
    
    /**
     * 获取两个日期之间的月数
     */
    public static int getMonthSpace(String date1, String date2) {
    	
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
			c1.setTime(sdf.parse(date1));
	        c2.setTime(sdf.parse(date2));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);
    	
    }

	public static int daysBetween(String dateStr,Date date2)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date1 = null;
		try {
			date1 = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		return days;
	}

	public static void main(String[] args) {
      System.out.println(daysBetween("20180804", DateUtil.getCurrentDate()));
    	
	}
}

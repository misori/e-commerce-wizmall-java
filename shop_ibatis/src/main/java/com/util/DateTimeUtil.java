package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

import org.junit.Test;

public class DateTimeUtil {

    public static String getDateTimeByPattern(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                pattern, Locale.KOREA);
        String dateString = formatter.format(new Date());

        return dateString;
    }

    /**
     * 입력데이타(YYYYMMDD)를 사용자 패턴을 사용하여 변경
     * @param dateTime
     * @param pattern
     * @return 예)YYYY-MM-DD
     */
    public static String getParseDateString(String dateTime, String pattern){
		if ( dateTime != null ){
			String year = dateTime.substring(0, 4);
			String month = dateTime.substring(4, 6);
			String day = dateTime.substring(6, 8);

			return year + pattern + month + pattern + day;
		} else {
			return "";
		}
    }

    /**
     * 어제날짜 구하기
     * @return
     */
    public Date getYesterday(){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, -1);
    	return cal.getTime();

    }

    /**
     * @deprecated
     */
    @Test
    public void getTest(){
    	Integer year, month, day;
    	year	= 2011;
    	month	= 10;
    	day	= 1;
    	getStartMonOfWeek(year, month, day);
    }

    public final String[] WeekNameKor = {"일","월","화","수","목","금","토"};

    /**
     * 특정 날짜와 같은 주에 있는  월요일의 날짜 구하기
     */
    @SuppressWarnings("static-access")
	public HashMap<String, Integer> getStartMonOfWeek(Integer year, Integer month, Integer day){
    	Calendar cal = Calendar.getInstance();
    	cal.set(year, month-1, day); //month는 0부터 시작한다.

		Calendar rtnCal	= getStartSunOfWeek(cal);
		rtnCal.add(Calendar.DATE, 1);//받아온 값은 일요일이므로 월요일이되려면 1일을 더해준다.

		HashMap<String, Integer> rtnParams = new HashMap<String, Integer>();
		rtnParams.put("Year", rtnCal.get(rtnCal.YEAR));
		rtnParams.put("Month", rtnCal.get(rtnCal.MONTH)+1);
		rtnParams.put("Day", rtnCal.get(rtnCal.DAY_OF_MONTH));

		return rtnParams;
    }

    /**
     * 특정 날짜와 같은 주에 있는  일요일의 날짜 구하기
     */
    @SuppressWarnings("static-access")
	public HashMap<String, Integer> getStartSunOfWeek(Integer year, Integer month, Integer day){
    	Calendar cal = Calendar.getInstance();
    	cal.set(year, month-1, day); //month는 0부터 시작한다.

		Calendar rtnCal	= getStartSunOfWeek(cal);
		//rtnCal.add(Calendar.DATE, 1);//받아온 값은 일요일이므로 월요일이되려면 1일을 더해준다.

		HashMap<String, Integer> rtnParams = new HashMap<String, Integer>();
		rtnParams.put("Year", rtnCal.get(rtnCal.YEAR));
		rtnParams.put("Month", rtnCal.get(rtnCal.MONTH)+1);
		rtnParams.put("Day", rtnCal.get(rtnCal.DAY_OF_MONTH));

		return rtnParams;
    }
    /**
     * 특정 날짜의 첫번째 요일(일요일)의 날짜 구하기
     */
    public Calendar getStartSunOfWeek(Calendar cal)//public static Date getWeekStart(Date date, int weekStart)
    {

        while (cal.get(Calendar.DAY_OF_WEEK) != 1)//일요일(1)이 아닐경우 날짜를 계속해서 빼줌, 날짜가 속한 주의 요일(1:일, 2:월, 3:화....)
        {
        	cal.add(Calendar.DATE, -1);
        }

		return cal;
    }



    /**
     *
     * @param cal :cal.set(inYear, inMonth, inDay+i)
     * @deprecated
     */
    public void getMonday(Calendar cal){

    	cal.get(cal.DAY_OF_WEEK);

    }







    /**
     * 아래 처럼 처리할 경우 month가 겹칠경우  의도하지 않는 데이타가 나온다.
     * @deprecated
     */
    @SuppressWarnings("static-access")
    public void text(){
    	Integer year, month, day;
    	year	= 2011;
    	month	= 10;
    	day	= 28;

    	Calendar cal = Calendar.getInstance();

    	//현재 넘어온 날짜를 세팅한다.
    	cal.set(year, month-1, day);
    	Integer weekday	=  cal.get(cal.DAY_OF_WEEK);//날짜가 속한 주의 요일(1:일, 2:월, 3:화....)
    	weekday	= weekday == 1 ? 7 : weekday - 2;//월요일로 세팅

    	//System.out.println(year+"-"+month+"-"+day);
    	cal.set(year, month, day-weekday);  //해당주일요일로 세팅

		int intYear		= cal.get(cal.YEAR);
		int intMonth	= cal.get(cal.MONTH);
		int intDay		= cal.get(cal.DAY_OF_MONTH);
		//System.out.println(intYear+"-"+intMonth+"-"+intDay);

    	//String monday	= cal.get(cal.DAY_OF_WEEK)+"년"+Integer.toString(cal.get(cal.MONTH)+1)+"월"+Integer.toString(cal.get(cal.DAY_OF_MONTH));
    	//System.out.println("monday:"+monday);

    }

    /**
     * 특정해의 달의 총일수와 시작 요일
     * @deprecated
     */
    public void test2(){
		GregorianCalendar calendar = new GregorianCalendar();
		//  String[] months = new String[] { "NOV", "DEC", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT" };

		for(int i=0;i<12;i++){
			calendar.set(2011,i,1);
			//System.out.println("startDay of Month " + calendar.get(Calendar.DAY_OF_WEEK));
			//System.out.println("max Days: " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
	}

    /**
     * @deprecated
     */
    public void test3(){
    	GregorianCalendar calendar = new GregorianCalendar();
    	  java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
    	  //String date = formatter.format(GregorianCalendar.getTime());
    	 // return formattedDate.toUpperCase();


    	 // System.out.println("DAY_OF_WEEK_IN_MONTH: "+ calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		//System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
		//System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
		//System.out.println("MONTH: " + calendar.get(GregorianCalendar.MONTH));
		//System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
		//System.out.println("First Day: " + calendar.getLeastMaximum(calendar.getFirstDayOfWeek()));
    }
}

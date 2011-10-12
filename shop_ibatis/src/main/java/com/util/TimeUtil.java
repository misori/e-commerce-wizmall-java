package com.util;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TimeUtil {

	//현재 날짜를 가져옮
	@Test
	//public void mkCurrentData(){
	public Date mkCurrentData(){
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		date = cal.getTime();
		//System.out.println(date);
		return date;//Tue Oct 11 15:16:29 KST 2011
	}
}

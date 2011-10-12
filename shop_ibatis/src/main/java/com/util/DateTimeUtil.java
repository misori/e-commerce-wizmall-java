package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {

    public static String getDateTimeByPattern(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                pattern, Locale.KOREA);
        String dateString = formatter.format(new Date());

        return dateString;
    }

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
}

package com.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class StringUtil {
	public String stackTraceToString(Throwable ex) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(b);
		ex.printStackTrace(p);
		p.close();
		String stackTrace = b.toString();
		try {
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return convertHtmlBr(stackTrace);
	}

	/**
	 * 개행문자를 br 로 출력
	 * @param   str         원본 문자열
	 * @return
	 */
	public String convertHtmlBr(String str) {
		if (str == null) return "";

		int length = str.length();
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < length; i++) {
			String tmp = str.substring(i, i + 1);
			if ("\r".compareTo(tmp) == 0) {
				tmp = str.substring(++i, i + 1);
				if ("\n".compareTo(tmp) == 0) buffer.append("<br>\r");
				else buffer.append("\r");
			}
			buffer.append(tmp);
		}
		return buffer.toString();
	}

	/**
	 *  원본 문자열에서 특정 문자열 찾기(php의 strstr을 변형)
	 *	@param haystack : 총길이의 문자열
	 *	@param needle : 찾는 문자
	 *	@return
	 */
	public int strstr(String haystack, String needle){
		for(int i = 0; i < haystack.length(); i++ ) {
			for(int j = 0; j < needle.length() && i+j < haystack.length(); j++ ) {
				if(needle.charAt(j) != haystack.charAt(i+j)) {
					break;
				} else if (j == needle.length()-1) {
					return i;
				}
			}
		}
		return -1;
	}

/*
	public static String fromCharCode(int... codePoints) {
	    StringBuilder builder = new StringBuilder(codePoints.length);
	    for (int codePoint : codePoints) {
	        builder.append(Character.toChars(codePoint));
	    }
	    return builder.toString();
	}
	*/
	public String fromCharCode(int... codePoints) {
	    return new String(codePoints, 0, codePoints.length);
	}

	 /**
     * 양쪽으로 자리수만큼 문자 채우기
     *
     * @param   str         원본 문자열
     * @param   size        총 문자열 사이즈(리턴받을 결과의 문자열 크기)
     * @param   strFillText 원본 문자열 외에 남는 사이즈만큼을 채울 문자
     * @return
     */
    public String getCPad(String str, int size, String strFillText) {
        int intPadPos = 0;
        for(int i = (str.getBytes()).length; i < size; i++) {
            if(intPadPos == 0) {
                str += strFillText;
                intPadPos = 1;
            } else {
                str = strFillText + str;
                intPadPos = 0;
            }
        }
        return str;
    }


    /**
     * 왼쪽으로 자리수만큼 문자 채우기
     *
     * @param   str         원본 문자열
     * @param   size        총 문자열 사이즈(리턴받을 결과의 문자열 크기)
     * @param   strFillText 원본 문자열 외에 남는 사이즈만큼을 채울 문자
     * @return
     */
    public String getLPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str = strFillText + str;
        }
        return str;
    }


    /**
     * 오른쪽으로 자리수만큼 문자 채우기
     *
     * @param   str         원본 문자열
     * @param   size        총 문자열 사이즈(리턴받을 결과의 문자열 크기)
     * @param   strFillText 원본 문자열 외에 남는 사이즈만큼을 채울 문자
     * @return
     */
    public String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
    }

    /**
     * 공백 문자를 null 문자로 돌리기
     */
    public String emptyToNull(String str){
    	str = str == "" ? null:str;
    	return str;
    }

    /**
     *  숫자인지를 체크하는 함수(문자열이 있을 경우)
     * @param str
     * @return
     */
    public boolean isNumber(String str){
    	boolean check = true;
    	for(int i = 0; i < str.length(); i++) {
	    	if(!Character.isDigit(str.charAt(i)))
	    	{
		    	check = false;
		    	break;
	    	}// end if
    	} //end for
    	return check;

        //or  return s.replaceAll("[+-]?\\d+", "").equals("") ? true : false;
        //or  java.util.regex.Pattern pattern = Pattern.compile("[+-]?\\d+");
       // return pattern.matcher(s).matches();
    }

    /**
     * 숫자인지를 체크하는 함수(문자열이 공백이거나 null일경우도 포함)
     * @param str
     * @return
     */
    public boolean isInt(String str){
    	boolean check = true;
    	if(str == null) check = false;
    	else if(str.equals(""))	check = false;
    	else check = isNumber(str);
    	return check;
    }

    /**
     * 문자를 숫자로 변경한다. 만약 문자열이 null이거나 공백, 혹은 숫자가 아닐경우는 0을 리턴한다.
     * @param str
     * @return
     */
    public int strToint(String str){
    	if(isInt(str)){
    		return Integer.parseInt(str);
    	}else return 0;
    }

    /**
     * 숫자가 null일경우 기본 0으로 세팅
     * @param i
     * @return
     */
    public int NullToZero(Integer i){

    	if(i == null){
    		return 0;
    	}else return i;
    }


    public String intTostr(Integer i){
    	if(i != null){
    		return Integer.toString(i);
    	}else return "0";
    }

}

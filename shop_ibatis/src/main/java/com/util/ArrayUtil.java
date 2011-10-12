package com.util;

public class ArrayUtil {
	/**
	 * dumpArray : 배열을 문자열로 출력
	 * @param array
	 */
	public static void dumpArray(String[] array) {
	    for (int i = 0; i < array.length; i++)
	      System.out.format("array[%d] = %s%n", i, array[i]);
	  }
}

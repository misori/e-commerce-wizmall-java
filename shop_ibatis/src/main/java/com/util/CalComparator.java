package com.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

public class CalComparator{

	/*
	 * 사용예
	 *  Set<String> set = addr.keySet();
        Object []hmKeys = set.toArray();
        Arrays.sort(hmKeys, keyStringSort); // 키값 정렬하기
        for(int i = 0; i < hmKeys.length; i++)
        {
            String key = (String)hmKeys[i];
            System.out.println(key);
        }
	 */

	/**
	 * KEY 가 String 인것에 대한 정렬
	 */
    public static Comparator<Object> keyStringSort = new Comparator<Object>() {
        public int compare(Object s1, Object s2) {
            String ss1 = (String)s1;
            String ss2 = (String)s2;
            return (-1) * ss2.compareTo(ss1);
        }
    };

    /**
     * KEY 가 Int 인것에 대한 정렬
     */
    public static Comparator<Object> keyIntegerSort = new Comparator<Object>() {
        public int compare(Object s1, Object s2) {
            Integer ss1 = (Integer)s1;
            Integer ss2 = (Integer)s2;
            return (-1) * ss2.compareTo(ss1);
        }
    };
}

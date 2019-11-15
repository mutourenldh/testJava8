package com.java8.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormatThreadLocal {
	//申明一个ThreadLocal,并且初始化它
	private static final ThreadLocal<DateFormat> df=new ThreadLocal<DateFormat>() {
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}
	};
	
	public static Date convert(String source) throws Exception {
		return df.get().parse(source);
		
	}
}

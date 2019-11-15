package com.java8.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//原来的simpleDateFormat类存在线程安全问题
public class TestSimpleDateFormat {
	
	public static void main(String[] args) throws Exception {
//		SimpleDateFormat format = new  SimpleDateFormat("yyyyMMdd");
//		Callable<Date> task = new Callable<Date>() {
//
//			@Override
//			public Date call() throws Exception {
//				return DateFormatThreadLocal.convert("20191012");
//			}
////			@Override
////			public Date call() throws Exception {
////				return format.parse("20191012");
////			}
//		};
//		ExecutorService pool = Executors.newFixedThreadPool(10);
//		
//		ArrayList<Future<Date>> list = new ArrayList<Future<Date>>();
//		for (int i = 0; i < 10; i++) {
//			list.add(pool.submit(task));
//		}
//		for (Future<Date> future : list) {
//			System.out.println(future.get());
//		}
		//java8中提供了线程安全的处理时间格式化的类,LocalDate,LocalTime,LocalDateTime类的实例是不可变的对象
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMdd");
		Callable<LocalDate> task = new Callable<LocalDate>() {
			@Override
			public LocalDate call() throws Exception {
				return LocalDate.parse("20190920",pattern);
			}
		};
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		ArrayList<Future<LocalDate>> list = new ArrayList<Future<LocalDate>>();
		for (int i = 0; i < 10; i++) {
			list.add(pool.submit(task));
		}
		for (Future<LocalDate> future : list) {
			System.out.println(future.get());
		}
		
		
	}

}

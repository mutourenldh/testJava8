package com.java8.time;

import org.junit.Test;

import java.sql.SQLOutput;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTime {
	@Test
	public void test1() {
		LocalTime now = LocalTime.now();
		LocalTime localDate = now.plusHours(2);
		System.out.println(localDate);
		//获得当前时间ISO标准的
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		//指定时间
		LocalDateTime ldt2 = LocalDateTime.of(2019, 10, 16, 12, 12,12);
		System.out.println(ldt2);
		//在原来的时间基础上进行时间的增加，使用plus对应的方法
		LocalDateTime ldt3 = ldt2.plusYears(2);
		System.out.println(ldt3);
		
		//在原来的时间基础上进行时间的减少，使用minus对应的方法
		LocalDateTime ldt4 = ldt2.minusMonths(2);
		System.out.println(ldt4);
		
		System.out.println("---------------------");
		//获取LocalDateTime对象中年月日时分秒
		System.out.println(ldt.getYear());
		System.out.println(ldt.getDayOfYear());
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getHour());
	}
	//2.以unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值
	@Test
	public void test2() {
		//
		Instant ins1 = Instant.now();
		System.out.println(ins1);
		//对该时间偏离的时区数
		OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
		System.out.println(odt);
		//显示从1970年到指定时间的毫秒数
		System.out.println(ins1.toEpochMilli());
		//显示从1970年增加多长的时间
		Instant ins2 = Instant.ofEpochSecond(60);
		System.out.println(ins2);
	}
//duration:"计算两个时间之间的间隔"
//	Period:"计算两个日期之间的间隔"
	@Test
	public void test3(){
		Instant ins1 = Instant.now();

		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Instant ins2 = Instant.now();
		//Duration 计算两个时间之间的间隔
		Duration between = Duration.between(ins1, ins2);
		System.out.println(between.toMillis());


		System.out.println("-------------------------");
		LocalTime date1 = LocalTime.now();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LocalTime date2 = LocalTime.now();
		System.out.println(Duration.between(date1,date2).toMillis());

	}

	@Test
	public void test4() {
		LocalDate date1 = LocalDate.of(2015, 1, 1);
		LocalDate date2 = LocalDate.now();
		Period between = Period.between(date1, date2);
		System.out.println(between);

		System.out.println(between.getYears());
		System.out.println(between.getMonths());
		System.out.println(between.getDays());
	}

	/**
	 * TemporalAdjuster	时间矫正器 有时候我们可能需要获取例如：将星期调整到下个周日等操作
	 * TemporalAdjusters  该类通过静态方法提供了大量的常用TemporalAdjuster的实现
	 */
	@Test
	public void test5() {
		LocalDateTime ldt1 = LocalDateTime.now();
		System.out.println(ldt1);

		LocalDateTime ldt2 = ldt1.withDayOfMonth(10);
		System.out.println(ldt2);

		LocalDateTime ldt3 = ldt1.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		System.out.println(ldt3);

		//自定义：下一个工作日
		LocalDateTime ldt5 = ldt1.with((l) -> {
			LocalDateTime ldt4 = (LocalDateTime) l;
			DayOfWeek dayOfWeek = ldt4.getDayOfWeek();
			if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
				return ldt4.plusDays(3);
			} else if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
				return ldt4.plusDays(2);
			} else {
				return ldt4.plusDays(1);
			}
		});
		System.out.println(ldt5);
	}
	//DateTimeFormatter:格式化时间/日期
	@Test
	public void test6() {
		DateTimeFormatter dtf=DateTimeFormatter.ISO_DATE;
		LocalDateTime ldt = LocalDateTime.now();

		String strDate = ldt.format(dtf);
		System.out.println(strDate);

		System.out.println("---------------------------");

		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

		String strDate2 = dtf2.format(ldt);
		System.out.println(strDate2);

		LocalDateTime parse = ldt.parse(strDate2, dtf2);
		System.out.println(parse);
	}
	//java8中加入了对时区的支持，带时区的时间分别为 ZonedDate,ZonedTime,ZonedDateTime
	//Zoneld:该类中包含了所有时区的信息
	//getAvailableZonelds() 可以获取所有时区信息
	//of(id) 用指定的时区信息获取Zoneld对象
@Test
	public void test7(){
		Set<String> set = ZoneId.getAvailableZoneIds();
		set.forEach(System.out::println);
	}

	@Test
	public void test8(){
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
		System.out.println(ldt);

		ZonedDateTime zonedDateTime = ldt.atZone(ZoneId.of("Asia/Shanghai"));
		System.out.println(zonedDateTime);
	}
}

package com.scream.study.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTest {
	@Test
	public void testLocalDate() {
//		LocalDate localDate = LocalDate.now();
//		LocalDate localDate = LocalDate.parse("20120-01-14");
		LocalDate localDate = LocalDate.of(2020, 01, 14);
		System.out.println(localDate);
		System.out.println("年" + localDate.getYear());
		System.out.println("月" + localDate.getMonth());
		System.out.println("月 int" + localDate.getMonthValue());
		System.out.println("星期几" + localDate.getDayOfWeek());
		System.out.println("星期几 int" + localDate.getDayOfWeek().getValue());

		LocalDate changeDate = localDate.plusYears(1);
		System.out.println(changeDate.isBefore(localDate));
		System.out.println(changeDate.isAfter(localDate));
		System.out.println(localDate.plusMonths(1));
		System.out.println(localDate.minusDays(1));
		System.out.println(localDate.minusWeeks(1));
	}

	@Test
	public void testLocalDateTime() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		ldt = LocalDateTime.of(2020, 11, 11, 8, 20, 30);
		System.out.println(ldt);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String ldtStr = dtf.format(ldt);
		System.out.println(ldtStr);

	}

	@Test
	public void testDuration() {
		LocalDateTime today = LocalDateTime.now();
		System.out.println(today);
		LocalDateTime changeDate = LocalDateTime.of(2020, 10, 1, 10, 40, 30);
		System.out.println(changeDate);
		Duration duration = Duration.between(today, changeDate);//第⼆个参数减第⼀个参数
		System.out.println(duration.toDays());//两个时间差的天数
		System.out.println(duration.toHours());//两个时间差的⼩时数
		System.out.println(duration.toMinutes());//两个时间差的分钟数
		System.out.println(duration.toMillis());//两个时间差的毫秒数
		System.out.println(duration.toNanos());//两个时间差的纳秒数
	}

	@Test
	public void instant() {
		System.out.println(Instant.now());
	}

	public void transfer() {
		Date date = Date.from(Instant.now());//新的时间戳转换为旧的Date
		Instant instant = date.toInstant();//

		// 新旧时间之间的互相转换依赖于Instant时间戳
		//Date转换为LocalDateTime
		LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());//LocalDateTime转换为Date
		date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());//LocalDate转换为Date
	}
}

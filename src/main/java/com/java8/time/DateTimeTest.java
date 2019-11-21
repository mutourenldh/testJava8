package com.java8.time;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lidonghao
 * @create 2019-11-19 11:32
 */
public class DateTimeTest {
    public static void main(String[] args) {
//      java.time.Instant和java.util.Date可以相互转
        Date date = new Date();
        Instant instant = date.toInstant();
        Date from = Date.from(instant);
//      Instant和TimeStamp相互转换
        Timestamp timestamp = new Timestamp(2019);
        Instant instant1 = timestamp.toInstant();
        Timestamp from1 = Timestamp.from(instant1);
//      LocalDate和Date
        LocalDate now = LocalDate.now();
        java.sql.Date date1 = java.sql.Date.valueOf(now);
        LocalDate localDate = date1.toLocalDate();
        //LocalTime和java.sql.Time
        LocalTime now1 = LocalTime.now();
        Time time = Time.valueOf(now1);
        LocalTime localTime = time.toLocalTime();
        //java.time.format.DateTimeFormatter和java.text.DateFormat
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:ss");
        Format format1 = format.toFormat();
    }

}

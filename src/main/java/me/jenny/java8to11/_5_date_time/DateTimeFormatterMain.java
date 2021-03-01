package me.jenny.java8to11._5_date_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterMain {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        // 포매팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(formatter));

        // 파싱
        LocalDate date = LocalDate.parse("11/26/1990", formatter);
        System.out.println(date);
    }
}

package me.jenny.java8to11._5_date_time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class InstantMain {
    public static void main(String[] args) {
        // "기계용 시간"으로 표현하는 방법
        // Instant.now(): 현재 UTC (GMT)를 리턴한다.
        Instant now = Instant.now();

        System.out.println(now);    // 기준시 UTC, GMT
        System.out.println(now.atZone(ZoneId.of("UTC")));

        ZoneId zone = ZoneId.systemDefault();
        System.out.println("ZoneId: " + zone);
        System.out.println(now.atZone(zone));

        LocalDateTime localDateTimeNow = LocalDateTime.now();
        // 기존 LocalDateTime 에 연산하려면 TemporalUnit 이라고 나오는데, 이렇게 하면 확장이 안되므로
        // ChronoUnit 을 입력해야 한다고 외우자.
        LocalDateTime plus = localDateTimeNow.plus(10, ChronoUnit.DAYS);
        System.out.println(plus);
    }
}

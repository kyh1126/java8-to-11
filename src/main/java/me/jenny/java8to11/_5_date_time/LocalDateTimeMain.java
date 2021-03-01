package me.jenny.java8to11._5_date_time;

import java.time.*;

public class LocalDateTimeMain {
    public static void main(String[] args) {
        // "인류용 시간"으로 표현하는 방법
        // LocalDateTime.now(): 현재 시스템 Zone 에 해당하는(로컬) 일시를 리턴한다.
        // LocalDateTime.of(int, Month, int, int, int, int): 로컬의 특정 일시를 리턴한다.
        // ZonedDateTime.of(int, Month, int, int, int, int, ZoneId): 특정 Zone 의 특정 일시를 리턴한다.
        LocalDateTime now = LocalDateTime.now();    // 설정 없으면 서버(로컬) default zone 정보 참고해서 시간이 찍힌다.
        LocalDateTime birthday =
                LocalDateTime.of(1990, Month.NOVEMBER, 26, 6, 30, 0);

        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime nowInKorea = ZonedDateTime.now(seoulZoneId);
        System.out.println(nowInKorea);

        // 기계용 시간에서도 ZoneId 를 적용하여 ZonedDateTime 로 변환할 수 있다.
        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime = nowInstant.atZone(seoulZoneId);
        System.out.println(zonedDateTime);
    }
}

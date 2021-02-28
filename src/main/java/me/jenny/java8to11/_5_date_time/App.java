package me.jenny.java8to11._5_date_time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // 자바 8에 새로운 날짜와 시간 API 가 생긴 이유
        // - 그 전까지 사용하던 java.util.Date 클래스는 mutable 하기 때문에 thread safe 하지 않다.
        // - 클래스 이름이 명확하지 않다. Date 인데 시간까지 다룬다. 시간도 epoch time (Unix 서버가 처음 시간재기 시작한 1970/1/1)
        // - 버그 발생할 여지가 많다. (타입 안정성이 없고, 월이 0부터 시작한다거나..)
        // - 날짜 시간 처리가 복잡한 애플리케이션에서는 보통 Joda Time 을 쓰곤했다.
        Date date = new Date();
        long time = date.getTime();                         // 기계용 시간 (machine time)
        System.out.println(date);
        System.out.println(time);

        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        after3Seconds.setTime(time);                        // mutable!
        System.out.println(after3Seconds);

        // 월에 숫자도 입력이 되는데, type safety 하지 않다.
//        Calendar jennyBirthday = new GregorianCalendar(1990, 11, 26);
        Calendar jennyBirthday = new GregorianCalendar(1990, Calendar.NOVEMBER, 26);
        System.out.println(jennyBirthday.getTime());

        jennyBirthday.add(Calendar.DAY_OF_YEAR, 1); // mutable!
        System.out.println(jennyBirthday.getTime());

        // 자바 8에서 제공하는 Date-Time API
        // - JSR-310 스팩의 구현체를 제공한다.
        // - 디자인철학: Clear, Fluent, Immutable, Extensible

        // 주요 API
        // - 기계용 시간 (machine time)과 인류용 시간(human time)으로 나눌 수 있다.
        // - 기계용 시간은 EPOCH(1970년 1월 1일 0시 0분 0초)부터 현재까지의 타임스탬프를 표현한다.
        // - 인류용 시간은 우리가 흔히 사용하는 연,월,일,시,분,초 등을 표현한다.

        // 타임스탬프는 Instant 를 사용, 특정 날짜(LocalDate), 시간(LocalTime), 일시(LocalDateTime)를 사용할 수 있다.
        // 기간 표현은 Duration: 30초 동안 이렇게 시간 기반, Period: 몇 년 동안 이렇게 날짜 기반으로 할 수 있다.
        // DateTimeFormatter 를 사용해서 일시를 특정한 문자열로 포매팅할 수 있다.
    }
}

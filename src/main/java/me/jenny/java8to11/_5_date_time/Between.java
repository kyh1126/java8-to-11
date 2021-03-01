package me.jenny.java8to11._5_date_time;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Between {
    public static void main(String[] args) {
        // 기간을 표현하는 방법
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.NOVEMBER, 26);

        // Period: 인간용 시간 비교
        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));

        // Duration: 기계용 시간(Instant)갖고 비교
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now, plus);
        System.out.println(between.getSeconds());
    }
}

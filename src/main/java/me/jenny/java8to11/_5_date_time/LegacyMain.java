package me.jenny.java8to11._5_date_time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class LegacyMain {
    public static void main(String[] args) {
        Date date = new Date();
        // 레거시 API 지원
        Instant instant = date.toInstant();
        Date newDate = Date.from(instant);

        ZoneId systemZoneId = ZoneId.systemDefault();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime dateTime = gregorianCalendar.toInstant().atZone(systemZoneId).toLocalDateTime();
        ZonedDateTime zonedDateTime = dateTime.atZone(systemZoneId);
        GregorianCalendar from = GregorianCalendar.from(zonedDateTime);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
    }
}

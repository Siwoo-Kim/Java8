package com.java8.study.time;

import com.sun.corba.se.impl.oa.toa.TOA;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;

import static org.junit.Assert.assertEquals;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오후 12:39
 **/


public class InitTimePackageInstance {

    @Test
    public void nowDemo() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime canadaTime = ZonedDateTime.now();

        date = LocalDate.of(2015, 11, 23);
        time = LocalTime.of(23, 59, 59);
        dateTime = LocalDateTime.of(2015, 11, 23, 23, 59, 59);
        ZonedDateTime koreanTime = ZonedDateTime.of(dateTime, ZoneId.of("Asia/Seoul"));
        System.out.println(koreanTime);
    }

    @Test
    public void temporalFieldDemo() {
        LocalTime now = LocalTime.now();
        int minitue = now.get(ChronoField.MINUTE_OF_HOUR);
        assertEquals(minitue, now.getMinute()); // TemporalField은 년, 월, 일 등 날짜와 시간의 필드를 정의했다.
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        assertEquals(today.get(ChronoField.DAY_OF_YEAR)+ 1, tomorrow.get(ChronoField.DAY_OF_YEAR));
    }
}

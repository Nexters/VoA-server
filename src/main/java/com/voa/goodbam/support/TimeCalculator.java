package com.voa.goodbam.support;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeCalculator {
    public static long minusFromNowToMinute(LocalDateTime localDateTime){
        long nowTimeToLong = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        return (nowTimeToLong-localDateTime.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli())/1000/60;
    };
    public static long minusToMinute(LocalDateTime Subtracted, LocalDateTime Subtract){
        return (Subtracted.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli()
                - Subtract.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli())/1000/60;
    }
}

package com.notifications.app.rules.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtils {

    public static boolean isMoreThanOneDayAgo(LocalDateTime localDateTime) {
        return Duration.between(localDateTime,  LocalDateTime.now()).toDays() < 1;
    }

    public static boolean isMoreThanOneHourAgo(LocalDateTime localDateTime) {
        return Duration.between(localDateTime, LocalDateTime.now()).toHours() < 1;
    }

    public static boolean isMoreThanTwoMinutesAgo(LocalDateTime localDateTime) {
        return Duration.between(localDateTime, LocalDateTime.now()).toMinutes() < 2;
    }

}

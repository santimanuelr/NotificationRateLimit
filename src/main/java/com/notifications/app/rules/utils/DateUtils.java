package com.notifications.app.rules.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtils {

    public static boolean isMoreThanOneDay(LocalDateTime n) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(n, now);
        return duration.toDays() < 1;
    }

}

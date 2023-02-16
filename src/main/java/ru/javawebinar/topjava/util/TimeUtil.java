package ru.javawebinar.topjava.util;

import java.time.LocalTime;

public class TimeUtil {
    public static boolean isBetweenHalfOpen(LocalTime time, LocalTime startTime, LocalTime endTime) {
        return !time.isBefore(startTime) && time.isBefore(endTime);
    }
}

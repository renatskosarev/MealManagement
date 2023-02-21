package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenTime(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return !lt.isBefore(startTime) && !lt.isAfter(endTime);
    }

    public static boolean isBetweenDate(LocalDate ld, LocalDate startDate, LocalDate endDate) {
        return !ld.isBefore(startDate) && !ld.isAfter(endDate);
    }

    public static boolean isBetween(LocalDateTime dateTime, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return isBetweenTime(dateTime.toLocalTime(), startDateTime.toLocalTime(), endDateTime.toLocalTime()) &&
                isBetweenDate(dateTime.toLocalDate(), startDateTime.toLocalDate(), endDateTime.toLocalDate());
    }

    public static boolean isBetween(LocalDateTime dateTime,
                                    LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return isBetweenTime(dateTime.toLocalTime(), startTime, endTime) &&
                isBetweenDate(dateTime.toLocalDate(), startDate, endDate);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}


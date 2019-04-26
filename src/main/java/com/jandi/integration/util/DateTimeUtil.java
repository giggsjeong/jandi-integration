package com.jandi.integration.util;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static ZonedDateTime toGMTZonedDateTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(zoneIdOfGMT());
    }

    public static ZoneId zoneIdOfGMT() {
        return ZoneId.of("GMT");
    }

    public static LocalDateTime nowGMT() {
        return LocalDateTime.now(Clock.system(zoneIdOfGMT()));
    }

    public static String toGMTFormat(LocalDateTime localDateTime) {
        return localDateTime.atZone(zoneIdOfGMT()).format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}

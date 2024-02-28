package org.Diary.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    public static String getFormattedDate(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }
}

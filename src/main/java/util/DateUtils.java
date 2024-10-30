package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getCurrentDate() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return date.format(LocalDateTime.now());
    }

    public static String getCurrentDateShortFormat() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("MMM dd");
        return date.format(LocalDateTime.now()).toUpperCase();
    }

    public static String getLocalTimePlusDaysShortFormat(int days) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime result = currentDate.plusDays(days);
        return result.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public static String getFormattedDate(int days) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime futureDate = currentDate.plusDays(days);

        String formattedDate;
        if (currentDate.getMonth() == futureDate.getMonth()) {
            formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MMM d")).toLowerCase()
                    + " – "
                    + futureDate.format(DateTimeFormatter.ofPattern("d"));
        } else {
            formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MMM d")).toLowerCase()
                    + " – "
                    + futureDate.format(DateTimeFormatter.ofPattern("MMM d")).toLowerCase();
        }

        String[] parts = formattedDate.split(" – ");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = Character.toUpperCase(parts[i].charAt(0)) + parts[i].substring(1);
        }
        formattedDate = String.join(" – ", parts);

        return formattedDate;
    }
}
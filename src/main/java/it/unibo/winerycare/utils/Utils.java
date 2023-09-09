package it.unibo.winerycare.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;

/**
 * Utility class containing methods for date-related operations.
 */
public final class Utils {
    
    private Utils() {}

    /**
     * Converts a SQL date to a Java date.
     *
     * @param sqlDate The SQL date to convert.
     * @return The equivalent Java date, or null if the input is null.
     */
    public static java.util.Date sqlDateToDate(final java.sql.Date sqlDate) {
        return sqlDate == null ? null : new java.util.Date(sqlDate.getTime());
    }
    
    /**
     * Converts a Java date to a SQL date.
     *
     * @param date The Java date to convert.
     * @return The equivalent SQL date, or null if the input is null.
     */
    public static java.sql.Date dateToSqlDate(final java.util.Date date) {
        return date == null ? null : new java.sql.Date(date.getTime());
    }
    
    /**
     * Constructs a Java date from day, month, and year values.
     *
     * @param day   The day of the month.
     * @param month The month (1-12).
     * @param year  The year.
     * @return An Optional containing the constructed date, or empty if parsing fails.
     */
    public static Optional<java.util.Date> buildDate(final int day, final int month, final int year) {
        try {
            final String dateFormatString = "dd/MM/yyyy";
            final String dateString = day + "/" + month + "/" + year;
            final java.util.Date date = new SimpleDateFormat(dateFormatString, Locale.ITALIAN).parse(dateString);
            return Optional.of(date);
        } catch (final ParseException e) {
            return Optional.empty();
        }
    }
}

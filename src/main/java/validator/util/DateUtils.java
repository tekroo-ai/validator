package validator.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for date validation operations.
 */
public final class DateUtils {

    /**
     * Private constructor to prevent instantiation.
     * 
     * @throws UnsupportedOperationException always
     */
    private DateUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Validates if the given input string can be parsed according to the specified date pattern.
     * 
     * @param input the date string to validate
     * @param pattern the date pattern to use for validation
     * @return true if the input is a valid date according to the pattern, false otherwise
     */
    public static boolean isValidDate(String input, String pattern) {
        if (input == null || pattern == null) {
            return false;
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            formatter.parse(input);
            return true;
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return false;
        }
    }
}

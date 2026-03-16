package validator.util;

/**
 * Utility class for string operations.
 */
public final class StringUtils {

    /**
     * Private constructor to prevent instantiation.
     * 
     * @throws UnsupportedOperationException always
     */
    private StringUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Trims whitespace from the input string and returns null if the result is blank.
     * 
     * @param input the string to trim
     * @return null if input is null or blank after trimming, otherwise the trimmed string
     */
    public static String trimToNull(String input) {
        if (input == null) {
            return null;
        }
        
        String trimmed = input.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    /**
     * Truncates the input string to the specified maximum length.
     * 
     * @param input the string to truncate
     * @param maxLength the maximum length for the result
     * @return null if input is null, empty string if maxLength is 0,
     *         the original string if its length is <= maxLength,
     *         otherwise the string truncated to maxLength characters
     */
    public static String truncate(String input, int maxLength) {
        if (input == null) {
            return null;
        }
        
        if (maxLength == 0) {
            return "";
        }
        
        if (input.length() <= maxLength) {
            return input;
        }
        
        return input.substring(0, maxLength);
    }
}

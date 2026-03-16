package validator.util;

import java.util.regex.Pattern;

/**
 * Utility class for input validation.
 */
public final class InputValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    private static final Pattern URL_PATTERN = Pattern.compile(
        "^https?://[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,})?(?:/.*)?$"
    );

    /**
     * Private constructor to prevent instantiation.
     * 
     * @throws UnsupportedOperationException always
     */
    private InputValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Checks if the input string contains only alphanumeric characters (letters and digits).
     * 
     * @param input the string to validate
     * @return true if input contains only letters and digits, false otherwise
     */
    public static boolean isAlphanumeric(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Checks if the input string contains only alphabetic characters (letters).
     * 
     * @param input the string to validate
     * @return true if input contains only letters, false otherwise
     */
    public static boolean isAlpha(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Validates if the input string is a valid email address.
     * 
     * @param input the string to validate
     * @return true if input is a valid email address, false otherwise
     */
    public static boolean validateEmail(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        return EMAIL_PATTERN.matcher(input).matches();
    }

    /**
     * Validates if the input string is a valid URL.
     * 
     * @param input the string to validate
     * @return true if input is a valid URL with http or https scheme, false otherwise
     */
    public static boolean validateUrl(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        return URL_PATTERN.matcher(input).matches();
    }
}

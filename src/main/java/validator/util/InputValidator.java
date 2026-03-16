package validator.util;

/**
 * Utility class for input validation.
 */
public final class InputValidator {

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
}

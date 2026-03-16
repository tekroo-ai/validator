package validator.util;

/**
 * Utility class for number validation.
 * 
 * This class provides static methods for validating whether strings represent
 * valid integers and doubles.
 */
public final class NumberUtils {

    /**
     * Private constructor to prevent instantiation of utility class.
     * 
     * @throws UnsupportedOperationException always
     */
    private NumberUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Checks if the given string represents a valid integer.
     * 
     * @param input the string to validate
     * @return true if the input represents a valid integer, false otherwise
     */
    public static boolean isInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the given string represents a valid double.
     * 
     * @param input the string to validate
     * @return true if the input represents a valid double, false otherwise
     */
    public static boolean isDouble(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

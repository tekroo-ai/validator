package validator.exception;

/**
 * Custom exception for validation errors.
 */
public class ValidationException extends RuntimeException {
    
    /**
     * Creates a ValidationException with the specified message.
     * 
     * @param message the detail message
     */
    public ValidationException(String message) {
        super(message);
    }
}

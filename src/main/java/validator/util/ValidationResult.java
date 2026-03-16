package validator.util;

/**
 * Represents the result of a validation operation.
 */
public final class ValidationResult {
    
    private final boolean valid;
    private final String message;
    
    /**
     * Private constructor to create a ValidationResult.
     * 
     * @param valid whether the validation passed
     * @param message the validation message, may be null
     */
    private ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }
    
    /**
     * Creates a ValidationResult with the given validity and message.
     * 
     * @param valid whether the validation passed
     * @param message the validation message, may be null
     * @return a ValidationResult instance
     */
    public static ValidationResult of(boolean valid, String message) {
        return new ValidationResult(valid, message);
    }
    
    /**
     * Creates a ValidationResult representing a successful validation.
     * 
     * @return a ValidationResult with valid=true and message=null
     */
    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }
    
    /**
     * Creates a ValidationResult representing a failed validation.
     * 
     * @param message the validation error message
     * @return a ValidationResult with valid=false and the given message
     */
    public static ValidationResult invalid(String message) {
        return new ValidationResult(false, message);
    }
    
    /**
     * Returns whether the validation passed.
     * 
     * @return true if the validation passed, false otherwise
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * Returns the validation message.
     * 
     * @return the validation message, may be null
     */
    public String getMessage() {
        return message;
    }
}

package validator.exception;

/**
 * Custom exception for field-specific validation errors.
 */
public class FieldValidationException extends ValidationException {
    
    private final String fieldName;
    
    /**
     * Creates a FieldValidationException with the specified field name and message.
     * 
     * @param fieldName the name of the field that failed validation
     * @param message the detail message
     * @throws NullPointerException if fieldName or message is null
     */
    public FieldValidationException(String fieldName, String message) {
        super(message);
        if (fieldName == null) {
            throw new NullPointerException("Field name cannot be null");
        }
        if (message == null) {
            throw new NullPointerException("Message cannot be null");
        }
        this.fieldName = fieldName;
    }
    
    /**
     * Returns the name of the field that failed validation.
     * 
     * @return the field name
     */
    public String getFieldName() {
        return fieldName;
    }
}

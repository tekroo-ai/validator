package validator.rule;

import validator.util.ValidationResult;

/**
 * Validation rule that ensures input does not exceed a maximum length.
 */
public class MaxLengthRule implements ValidationRule {
    
    private final int maxLength;
    
    /**
     * Creates a MaxLengthRule with the specified maximum length.
     * 
     * @param maxLength the maximum allowed length
     */
    public MaxLengthRule(int maxLength) {
        this.maxLength = maxLength;
    }
    
    /**
     * Validates that the input length does not exceed the maximum length.
     * Returns valid for null input.
     * 
     * @param input the string to validate
     * @return ValidationResult.valid() if input is null or within length limit, ValidationResult.invalid() otherwise
     */
    @Override
    public ValidationResult validate(String input) {
        if (input == null) {
            return ValidationResult.valid();
        }
        
        if (input.length() > maxLength) {
            return ValidationResult.invalid("Input length exceeds maximum of " + maxLength);
        }
        
        return ValidationResult.valid();
    }
}

package validator.rule;

import validator.util.ValidationResult;

/**
 * Validation rule that ensures input is not null or blank.
 */
public class NotBlankRule implements ValidationRule {
    
    /**
     * Validates that the input is not null or blank.
     * 
     * @param input the string to validate
     * @return ValidationResult.valid() if input is not null or blank, ValidationResult.invalid() otherwise
     */
    @Override
    public ValidationResult validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return ValidationResult.invalid("Input cannot be null or blank");
        }
        return ValidationResult.valid();
    }
}

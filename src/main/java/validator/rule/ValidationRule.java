package validator.rule;

import validator.util.ValidationResult;

/**
 * Interface for validation rules that can validate string input.
 */
public interface ValidationRule {
    
    /**
     * Validates the given input string.
     * 
     * @param input the string to validate
     * @return ValidationResult indicating whether the input is valid
     */
    ValidationResult validate(String input);
}

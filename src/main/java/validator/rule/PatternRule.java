package validator.rule;

import validator.util.ValidationResult;
import java.util.regex.Pattern;

/**
 * Validation rule that ensures input matches a regular expression pattern.
 */
public class PatternRule implements ValidationRule {
    
    private final Pattern pattern;
    
    /**
     * Creates a PatternRule with the specified pattern.
     * 
     * @param pattern the regular expression pattern to match against
     */
    public PatternRule(Pattern pattern) {
        this.pattern = pattern;
    }
    
    /**
     * Validates that the input matches the pattern.
     * Returns invalid for null input.
     * 
     * @param input the string to validate
     * @return ValidationResult.valid() if input matches the pattern, ValidationResult.invalid() otherwise
     */
    @Override
    public ValidationResult validate(String input) {
        if (input == null) {
            return ValidationResult.invalid("Input cannot be null");
        }
        
        if (!pattern.matcher(input).matches()) {
            return ValidationResult.invalid("Input does not match required pattern");
        }
        
        return ValidationResult.valid();
    }
}

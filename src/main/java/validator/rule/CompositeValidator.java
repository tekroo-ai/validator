package validator.rule;

import validator.util.ValidationResult;
import java.util.List;

/**
 * Validator that orchestrates multiple ValidationRule instances.
 * Returns the first failure or success if all pass.
 */
public class CompositeValidator {
    
    private final List<ValidationRule> rules;
    
    /**
     * Creates a CompositeValidator with the specified list of rules.
     * 
     * @param rules the list of validation rules to execute
     */
    public CompositeValidator(List<ValidationRule> rules) {
        this.rules = rules;
    }
    
    /**
     * Validates input against all rules and returns the first failure or success if all pass.
     * 
     * @param input the string to validate
     * @return ValidationResult - first ValidationResult where isValid() is false, or ValidationResult.valid() if all pass
     */
    public ValidationResult validate(String input) {
        for (ValidationRule rule : rules) {
            ValidationResult result = rule.validate(input);
            if (!result.isValid()) {
                return result;
            }
        }
        return ValidationResult.valid();
    }
}

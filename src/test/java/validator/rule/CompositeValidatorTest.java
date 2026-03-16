package validator.rule;

import org.junit.jupiter.api.Test;
import validator.util.ValidationResult;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

class CompositeValidatorTest {
    
    @Test
    void validateReturnsValidWhenAllRulesPass() {
        List<ValidationRule> rules = List.of(
            new NotBlankRule(),
            new MaxLengthRule(10)
        );
        CompositeValidator validator = new CompositeValidator(rules);
        
        ValidationResult result = validator.validate("hello");
        assertTrue(result.isValid());
        assertNull(result.getMessage());
    }
    
    @Test
    void validateReturnsFirstRuleFailureWhenFirstRuleFails() {
        List<ValidationRule> rules = List.of(
            new NotBlankRule(),
            new MaxLengthRule(10)
        );
        CompositeValidator validator = new CompositeValidator(rules);
        
        ValidationResult result = validator.validate(null);
        assertFalse(result.isValid());
        assertEquals("Input cannot be null or blank", result.getMessage());
    }
    
    @Test
    void validateReturnsSecondRuleFailureWhenSecondRuleFails() {
        List<ValidationRule> rules = List.of(
            new NotBlankRule(),
            new MaxLengthRule(3)
        );
        CompositeValidator validator = new CompositeValidator(rules);
        
        ValidationResult result = validator.validate("hello");
        assertFalse(result.isValid());
        assertEquals("Input length exceeds maximum of 3", result.getMessage());
    }
    
    @Test
    void validateReturnsValidForEmptyRuleList() {
        List<ValidationRule> rules = new ArrayList<>();
        CompositeValidator validator = new CompositeValidator(rules);
        
        ValidationResult result = validator.validate("anything");
        assertTrue(result.isValid());
        assertNull(result.getMessage());
    }
}

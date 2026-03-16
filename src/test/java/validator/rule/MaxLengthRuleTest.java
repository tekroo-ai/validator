package validator.rule;

import org.junit.jupiter.api.Test;
import validator.util.ValidationResult;
import static org.junit.jupiter.api.Assertions.*;

class MaxLengthRuleTest {
    
    private final MaxLengthRule rule = new MaxLengthRule(5);
    
    @Test
    void validateReturnsValidForNullInput() {
        ValidationResult result = rule.validate(null);
        assertTrue(result.isValid());
        assertNull(result.getMessage());
    }
    
    @Test
    void validateReturnsValidForInputWithinLimit() {
        ValidationResult result = rule.validate("abc");
        assertTrue(result.isValid());
        assertNull(result.getMessage());
    }
    
    @Test
    void validateReturnsValidForInputExactlyAtLimit() {
        ValidationResult result = rule.validate("12345");
        assertTrue(result.isValid());
        assertNull(result.getMessage());
    }
    
    @Test
    void validateReturnsInvalidForInputExceedingLimit() {
        ValidationResult result = rule.validate("123456");
        assertFalse(result.isValid());
        assertEquals("Input length exceeds maximum of 5", result.getMessage());
    }
}

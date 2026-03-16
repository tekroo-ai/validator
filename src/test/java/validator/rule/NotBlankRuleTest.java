package validator.rule;

import org.junit.jupiter.api.Test;
import validator.util.ValidationResult;
import static org.junit.jupiter.api.Assertions.*;

class NotBlankRuleTest {
    
    private final NotBlankRule rule = new NotBlankRule();
    
    @Test
    void validateReturnsInvalidForNullInput() {
        ValidationResult result = rule.validate(null);
        assertFalse(result.isValid());
        assertEquals("Input cannot be null or blank", result.getMessage());
    }
    
    @Test
    void validateReturnsInvalidForBlankInput() {
        ValidationResult result = rule.validate("   ");
        assertFalse(result.isValid());
        assertEquals("Input cannot be null or blank", result.getMessage());
    }
    
    @Test
    void validateReturnsValidForNonBlankInput() {
        ValidationResult result = rule.validate("hello");
        assertTrue(result.isValid());
        assertNull(result.getMessage());
    }
}

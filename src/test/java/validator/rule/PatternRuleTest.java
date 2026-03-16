package validator.rule;

import org.junit.jupiter.api.Test;
import validator.util.ValidationResult;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

class PatternRuleTest {
    
    private final Pattern digitPattern = Pattern.compile("\\d+");
    private final PatternRule rule = new PatternRule(digitPattern);
    
    @Test
    void validateReturnsInvalidForNullInput() {
        ValidationResult result = rule.validate(null);
        assertFalse(result.isValid());
        assertEquals("Input cannot be null", result.getMessage());
    }
    
    @Test
    void validateReturnsValidForMatchingInput() {
        ValidationResult result = rule.validate("123");
        assertTrue(result.isValid());
        assertNull(result.getMessage());
    }
    
    @Test
    void validateReturnsInvalidForNonMatchingInput() {
        ValidationResult result = rule.validate("abc");
        assertFalse(result.isValid());
        assertEquals("Input does not match required pattern", result.getMessage());
    }
}

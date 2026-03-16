package validator.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationResultTest {

    @Test
    void ofCreatesValidationResultWithGivenValidityAndMessage() {
        ValidationResult result = ValidationResult.of(true, "Success");
        assertTrue(result.isValid());
        assertEquals("Success", result.getMessage());
        
        ValidationResult invalidResult = ValidationResult.of(false, "Error");
        assertFalse(invalidResult.isValid());
        assertEquals("Error", invalidResult.getMessage());
    }

    @Test
    void validReturnsValidationResultWithTrueValidityAndNullMessage() {
        ValidationResult result = ValidationResult.valid();
        assertTrue(result.isValid());
        assertNull(result.getMessage());
    }

    @Test
    void invalidReturnsValidationResultWithFalseValidityAndGivenMessage() {
        ValidationResult result = ValidationResult.invalid("Error message");
        assertFalse(result.isValid());
        assertEquals("Error message", result.getMessage());
    }

    @Test
    void isValidReturnsCorrectValidityStatus() {
        ValidationResult validResult = ValidationResult.valid();
        assertTrue(validResult.isValid());
        
        ValidationResult invalidResult = ValidationResult.invalid("Error");
        assertFalse(invalidResult.isValid());
    }

    @Test
    void getMessageReturnsCorrectMessage() {
        ValidationResult resultWithMessage = ValidationResult.of(false, "Test message");
        assertEquals("Test message", resultWithMessage.getMessage());
        
        ValidationResult resultWithNullMessage = ValidationResult.valid();
        assertNull(resultWithNullMessage.getMessage());
    }
}

package validator.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FieldValidationExceptionTest {
    
    @Test
    void constructorWithFieldNameAndMessageCreatesException() {
        String fieldName = "email";
        String message = "Invalid email format";
        FieldValidationException exception = new FieldValidationException(fieldName, message);
        
        assertEquals(fieldName, exception.getFieldName());
        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof ValidationException);
    }
    
    @Test
    void getFieldNameReturnsFieldName() {
        String fieldName = "username";
        String message = "Username is required";
        FieldValidationException exception = new FieldValidationException(fieldName, message);
        
        assertEquals(fieldName, exception.getFieldName());
    }
    
    @Test
    void constructorThrowsNullPointerExceptionForNullFieldName() {
        assertThrows(NullPointerException.class, () -> {
            new FieldValidationException(null, "message");
        });
    }
    
    @Test
    void constructorThrowsNullPointerExceptionForNullMessage() {
        assertThrows(NullPointerException.class, () -> {
            new FieldValidationException("fieldName", null);
        });
    }
}

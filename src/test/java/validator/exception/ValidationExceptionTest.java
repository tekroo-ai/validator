package validator.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationExceptionTest {
    
    @Test
    void constructorWithMessageCreatesException() {
        String message = "Validation failed";
        ValidationException exception = new ValidationException(message);
        
        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }
}

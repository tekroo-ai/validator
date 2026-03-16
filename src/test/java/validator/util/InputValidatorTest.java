package validator.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class InputValidatorTest {

    @Test
    void isAlphanumericReturnsTrueForLettersAndDigits() {
        assertTrue(InputValidator.isAlphanumeric("abc123"));
    }

    @Test
    void isAlphanumericReturnsFalseForNonAlphanumeric() {
        assertFalse(InputValidator.isAlphanumeric("abc123!"));
    }

    @Test
    void isAlphanumericReturnsFalseForNull() {
        assertFalse(InputValidator.isAlphanumeric(null));
    }

    @Test
    void isAlphanumericReturnsFalseForEmptyString() {
        assertFalse(InputValidator.isAlphanumeric(""));
    }

    @Test
    void isAlphaReturnsTrueForLettersOnly() {
        assertTrue(InputValidator.isAlpha("abc"));
    }

    @Test
    void isAlphaReturnsFalseForNonAlpha() {
        assertFalse(InputValidator.isAlpha("abc123"));
    }

    @Test
    void isAlphaReturnsFalseForNull() {
        assertFalse(InputValidator.isAlpha(null));
    }

    @Test
    void isAlphaReturnsFalseForEmptyString() {
        assertFalse(InputValidator.isAlpha(""));
    }

    @Test
    void constructorThrowsUnsupportedOperationException() throws Exception {
        Constructor<InputValidator> constructor = InputValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        
        try {
            constructor.newInstance();
            fail("Expected InvocationTargetException");
        } catch (InvocationTargetException e) {
            assertInstanceOf(UnsupportedOperationException.class, e.getCause());
        }
    }
}

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

    @Test
    void validateEmailReturnsTrueForValidEmail() {
        assertTrue(InputValidator.validateEmail("user@example.com"));
    }

    @Test
    void validateEmailReturnsFalseForInvalidEmailUserAtOnly() {
        assertFalse(InputValidator.validateEmail("user@"));
    }

    @Test
    void validateEmailReturnsFalseForInvalidEmailAtDomainOnly() {
        assertFalse(InputValidator.validateEmail("@example.com"));
    }

    @Test
    void validateEmailReturnsFalseForInvalidEmailPlaintext() {
        assertFalse(InputValidator.validateEmail("plaintext"));
    }

    @Test
    void validateEmailReturnsFalseForNull() {
        assertFalse(InputValidator.validateEmail(null));
    }

    @Test
    void validateEmailReturnsFalseForEmptyString() {
        assertFalse(InputValidator.validateEmail(""));
    }

    @Test
    void validateUrlReturnsTrueForValidHttpUrl() {
        assertTrue(InputValidator.validateUrl("http://example.com"));
    }

    @Test
    void validateUrlReturnsTrueForValidHttpsUrlWithPath() {
        assertTrue(InputValidator.validateUrl("https://example.com/path"));
    }

    @Test
    void validateUrlReturnsFalseForInvalidFtpUrl() {
        assertFalse(InputValidator.validateUrl("ftp://example.com"));
    }

    @Test
    void validateUrlReturnsFalseForInvalidUrlNoScheme() {
        assertFalse(InputValidator.validateUrl("example.com"));
    }

    @Test
    void validateUrlReturnsFalseForInvalidUrlPlaintext() {
        assertFalse(InputValidator.validateUrl("plaintext"));
    }

    @Test
    void validateUrlReturnsFalseForNull() {
        assertFalse(InputValidator.validateUrl(null));
    }

    @Test
    void validateUrlReturnsFalseForEmptyString() {
        assertFalse(InputValidator.validateUrl(""));
    }

    @Test
    void validatePhoneNumberReturnsTrueForPlusOneFormat() {
        assertTrue(InputValidator.validatePhoneNumber("+1-800-555-1234"));
    }

    @Test
    void validatePhoneNumberReturnsTrueForParenthesesFormat() {
        assertTrue(InputValidator.validatePhoneNumber("(800) 555-1234"));
    }

    @Test
    void validatePhoneNumberReturnsTrueForDashFormat() {
        assertTrue(InputValidator.validatePhoneNumber("800-555-1234"));
    }

    @Test
    void validatePhoneNumberReturnsFalseForNumbers() {
        assertFalse(InputValidator.validatePhoneNumber("12345"));
    }

    @Test
    void validatePhoneNumberReturnsFalseForLetters() {
        assertFalse(InputValidator.validatePhoneNumber("letters"));
    }

    @Test
    void validatePhoneNumberReturnsFalseForEmptyString() {
        assertFalse(InputValidator.validatePhoneNumber(""));
    }

    @Test
    void validatePhoneNumberReturnsFalseForNull() {
        assertFalse(InputValidator.validatePhoneNumber(null));
    }

    @Test
    void validateCreditCardReturnsTrueForValidVisaNumber() {
        assertTrue(InputValidator.validateCreditCard("4111111111111111"));
    }

    @Test
    void validateCreditCardReturnsFalseForLuhnFailure() {
        assertFalse(InputValidator.validateCreditCard("4111111111111112"));
    }

    @Test
    void validateCreditCardReturnsFalseForLetters() {
        assertFalse(InputValidator.validateCreditCard("411111111111111a"));
    }

    @Test
    void validateCreditCardReturnsFalseForWrongLength() {
        assertFalse(InputValidator.validateCreditCard("41111111111111"));
    }

    @Test
    void validateCreditCardReturnsFalseForNull() {
        assertFalse(InputValidator.validateCreditCard(null));
    }
}

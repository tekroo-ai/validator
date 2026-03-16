package validator.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void isValidDateReturnsTrueForValidDateWithYyyyMmDdPattern() {
        assertTrue(DateUtils.isValidDate("2023-12-25", "yyyy-MM-dd"));
    }

    @Test
    void isValidDateReturnsFalseForInvalidDateString() {
        assertFalse(DateUtils.isValidDate("invalid-date", "yyyy-MM-dd"));
    }

    @Test
    void isValidDateReturnsFalseForNullInput() {
        assertFalse(DateUtils.isValidDate(null, "yyyy-MM-dd"));
    }

    @Test
    void isValidDateReturnsFalseForNullPattern() {
        assertFalse(DateUtils.isValidDate("2023-12-25", null));
    }

    @Test
    void constructorThrowsUnsupportedOperationException() throws Exception {
        Constructor<DateUtils> constructor = DateUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        
        try {
            constructor.newInstance();
            fail("Expected UnsupportedOperationException");
        } catch (InvocationTargetException e) {
            assertInstanceOf(UnsupportedOperationException.class, e.getCause());
        }
    }
}

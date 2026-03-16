package validator.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class NumberUtilsTest {

    @Test
    void isIntegerReturnsTrueForValidInteger() {
        assertTrue(NumberUtils.isInteger("123"));
    }

    @Test
    void isIntegerReturnsFalseForInvalidInteger() {
        assertFalse(NumberUtils.isInteger("abc"));
    }

    @Test
    void isIntegerReturnsFalseForNull() {
        assertFalse(NumberUtils.isInteger(null));
    }

    @Test
    void isDoubleReturnsTrueForValidDouble() {
        assertTrue(NumberUtils.isDouble("123.45"));
    }

    @Test
    void isDoubleReturnsFalseForInvalidDouble() {
        assertFalse(NumberUtils.isDouble("abc"));
    }

    @Test
    void constructorThrowsUnsupportedOperationException() throws Exception {
        Constructor<NumberUtils> constructor = NumberUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        
        try {
            constructor.newInstance();
            fail("Expected InvocationTargetException");
        } catch (InvocationTargetException e) {
            assertInstanceOf(UnsupportedOperationException.class, e.getCause());
        }
    }
}

package validator.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class StringUtilsTest {

    @Test
    void trimToNullReturnsNullForNull() {
        assertNull(StringUtils.trimToNull(null));
    }

    @Test
    void trimToNullReturnsNullForBlankString() {
        assertNull(StringUtils.trimToNull("   "));
    }

    @Test
    void trimToNullReturnsNullForEmptyString() {
        assertNull(StringUtils.trimToNull(""));
    }

    @Test
    void trimToNullReturnsTrimmedStringForNonBlank() {
        assertEquals("hello", StringUtils.trimToNull("  hello  "));
    }

    @Test
    void truncateReturnsStringUnchangedWhenLengthLessThanOrEqualToMaxLength() {
        assertEquals("hello", StringUtils.truncate("hello", 5));
        assertEquals("hello", StringUtils.truncate("hello", 10));
    }

    @Test
    void truncateReturnsStringTruncatedWhenLengthGreaterThanMaxLength() {
        assertEquals("hel", StringUtils.truncate("hello", 3));
    }

    @Test
    void truncateReturnsNullForNull() {
        assertNull(StringUtils.truncate(null, 5));
    }

    @Test
    void truncateReturnsEmptyStringForMaxLengthZero() {
        assertEquals("", StringUtils.truncate("hello", 0));
    }

    @Test
    void constructorThrowsUnsupportedOperationException() throws Exception {
        Constructor<StringUtils> constructor = StringUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        
        try {
            constructor.newInstance();
            fail("Expected InvocationTargetException");
        } catch (InvocationTargetException e) {
            assertInstanceOf(UnsupportedOperationException.class, e.getCause());
        }
    }
}

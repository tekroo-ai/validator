package validator.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTest {

    @Test
    void isNullOrEmptyReturnsTrueForNullCollection() {
        Collection<String> collection = null;
        assertTrue(CollectionUtils.isNullOrEmpty(collection));
    }

    @Test
    void isNullOrEmptyReturnsTrueForEmptyCollection() {
        Collection<String> collection = new ArrayList<>();
        assertTrue(CollectionUtils.isNullOrEmpty(collection));
    }

    @Test
    void isNullOrEmptyReturnsFalseForNonEmptyCollection() {
        Collection<String> collection = Arrays.asList("test");
        assertFalse(CollectionUtils.isNullOrEmpty(collection));
    }

    @Test
    void filterNullsRemovesNullElementsFromList() {
        List<String> list = Arrays.asList("a", null, "b", null, "c");
        List<String> result = CollectionUtils.filterNulls(list);
        assertEquals(3, result.size());
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    void filterNullsReturnsListUnchangedWhenNoNulls() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> result = CollectionUtils.filterNulls(list);
        assertEquals(3, result.size());
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    void constructorThrowsUnsupportedOperationException() throws Exception {
        Constructor<CollectionUtils> constructor = CollectionUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        
        try {
            constructor.newInstance();
            fail("Expected constructor to throw UnsupportedOperationException");
        } catch (InvocationTargetException e) {
            assertInstanceOf(UnsupportedOperationException.class, e.getCause());
        }
    }
}

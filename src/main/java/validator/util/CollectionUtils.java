package validator.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Utility class for collection operations and validations.
 * This class provides static methods for common collection tasks
 * such as null/empty checking and filtering operations.
 */
public final class CollectionUtils {
    
    /**
     * Private constructor to prevent instantiation of utility class.
     * 
     * @throws UnsupportedOperationException always, as this is a utility class
     */
    private CollectionUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Checks if a collection is null or empty.
     * 
     * @param <T> the type of elements in the collection
     * @param collection the collection to check
     * @return true if the collection is null or empty, false otherwise
     */
    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }
    
    /**
     * Creates a new list with all null elements removed from the input list.
     * 
     * @param <T> the type of elements in the list
     * @param list the list to filter
     * @return a new list containing all non-null elements from the input list,
     *         or an empty list if the input is null
     */
    public static <T> List<T> filterNulls(List<T> list) {
        List<T> result = new ArrayList<>();
        if (list != null) {
            for (T element : list) {
                if (element != null) {
                    result.add(element);
                }
            }
        }
        return result;
    }
}

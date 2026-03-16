package validator.repository;

import validator.model.User;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User persistence operations.
 */
public interface UserRepository {
    
    /**
     * Saves a user to the repository.
     * 
     * @param user the user to save
     * @return the saved user with assigned id
     */
    User save(User user);
    
    /**
     * Finds a user by their id.
     * 
     * @param id the user id
     * @return the user if found, empty otherwise
     */
    Optional<User> findById(long id);
    
    /**
     * Retrieves all users from the repository.
     * 
     * @return list of all users
     */
    List<User> findAll();
    
    /**
     * Deletes a user by their id.
     * 
     * @param id the user id to delete
     */
    void deleteById(long id);
    
    /**
     * Finds all users with pagination support.
     * 
     * @param offset the number of users to skip
     * @param limit the maximum number of users to return
     * @return list of users within the specified range
     */
    List<User> findAll(int offset, int limit);
    
    /**
     * Finds users by name containing the specified query with pagination support.
     * 
     * @param query the search query to match against user names
     * @param offset the number of users to skip
     * @param limit the maximum number of users to return
     * @return list of users whose names contain the query within the specified range
     */
    List<User> findByNameContaining(String query, int offset, int limit);
}

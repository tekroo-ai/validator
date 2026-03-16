package validator.service;

import validator.exception.FieldValidationException;
import validator.model.User;
import validator.repository.UserRepository;
import validator.util.InputValidator;
import validator.util.ValidationResult;

import java.util.List;
import java.util.Optional;

/**
 * Service class for user operations.
 */
public class UserService {
    
    private final UserRepository userRepository;
    
    /**
     * Creates a new UserService with the given repository.
     * 
     * @param userRepository the user repository
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * Creates a new user with the given name and email.
     * 
     * @param name the user's name
     * @param email the user's email address
     * @return the created user
     * @throws FieldValidationException if email is invalid
     */
    public User createUser(String name, String email) {
        ValidationResult emailValidation = InputValidator.validateEmail(email);
        if (!emailValidation.isValid()) {
            throw new FieldValidationException("email", emailValidation.getMessage());
        }
        
        User user = new User(0, name, email);
        return userRepository.save(user);
    }
    
    /**
     * Finds a user by their id.
     * 
     * @param id the user id
     * @return the user if found, empty otherwise
     */
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
    
    /**
     * Lists all users.
     * 
     * @return list of all users
     */
    public List<User> listAll() {
        return userRepository.findAll();
    }
    
    /**
     * Lists users with pagination support.
     * 
     * @param page the page number (1-based)
     * @param pageSize the number of users per page
     * @return list of users for the specified page
     * @throws IllegalArgumentException if page < 1 or pageSize <= 0 or pageSize > 1000
     */
    public List<User> listPaged(int page, int pageSize) {
        if (page < 1) {
            throw new IllegalArgumentException("Page must be at least 1");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be positive");
        }
        if (pageSize > 1000) {
            throw new IllegalArgumentException("Page size cannot exceed 1000");
        }
        
        int offset = (page - 1) * pageSize;
        return userRepository.findAll(offset, pageSize);
    }
    
    /**
     * Searches users by name containing the specified query with pagination support.
     * 
     * @param query the search query to match against user names
     * @param page the page number (1-based)
     * @param pageSize the number of users per page
     * @return list of users whose names contain the query for the specified page
     * @throws IllegalArgumentException if page < 1 or pageSize <= 0 or pageSize > 1000
     */
    public List<User> searchByName(String query, int page, int pageSize) {
        if (page < 1) {
            throw new IllegalArgumentException("Page must be at least 1");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be positive");
        }
        if (pageSize > 1000) {
            throw new IllegalArgumentException("Page size cannot exceed 1000");
        }
        
        int offset = (page - 1) * pageSize;
        return userRepository.findByNameContaining(query, offset, pageSize);
    }
}

package validator.service;

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
     * @throws IllegalArgumentException if email is invalid
     */
    public User createUser(String name, String email) {
        ValidationResult emailValidation = InputValidator.validateEmail(email);
        if (!emailValidation.isValid()) {
            throw new IllegalArgumentException(emailValidation.getMessage());
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
}

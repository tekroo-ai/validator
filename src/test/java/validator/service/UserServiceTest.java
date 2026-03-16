package validator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validator.model.User;
import validator.repository.JdbiUserRepository;
import validator.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        // Reset schema - create new in-memory repository for each test
        UserRepository userRepository = new JdbiUserRepository();
        userService = new UserService(userRepository);
    }
    
    @Test
    void createUserWithValidEmail() {
        User user = userService.createUser("John Doe", "john@example.com");
        
        assertNotNull(user);
        assertTrue(user.id() > 0);
        assertEquals("John Doe", user.name());
        assertEquals("john@example.com", user.email());
    }
    
    @Test
    void createUserWithInvalidEmailThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> userService.createUser("John Doe", "invalid-email")
        );
        
        assertEquals("Invalid email format", exception.getMessage());
    }
    
    @Test
    void findById() {
        User createdUser = userService.createUser("Jane Doe", "jane@example.com");
        
        Optional<User> foundUser = userService.findById(createdUser.id());
        
        assertTrue(foundUser.isPresent());
        assertEquals(createdUser.id(), foundUser.get().id());
        assertEquals("Jane Doe", foundUser.get().name());
        assertEquals("jane@example.com", foundUser.get().email());
    }
    
    @Test
    void listAll() {
        userService.createUser("John Doe", "john@example.com");
        userService.createUser("Jane Doe", "jane@example.com");
        
        List<User> users = userService.listAll();
        
        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> "John Doe".equals(u.name())));
        assertTrue(users.stream().anyMatch(u -> "Jane Doe".equals(u.name())));
    }
}

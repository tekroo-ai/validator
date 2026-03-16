package validator.repository;

import validator.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory implementation of UserRepository for testing without external dependencies.
 */
public class JdbiUserRepository implements UserRepository {
    
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * Creates a new JdbiUserRepository.
     */
    public JdbiUserRepository() {
        // No-arg constructor for in-memory implementation
    }
    
    @Override
    public User save(User user) {
        if (user.id() == 0) {
            // Insert new user
            long newId = idGenerator.getAndIncrement();
            User newUser = new User(newId, user.name(), user.email());
            users.put(newId, newUser);
            return newUser;
        } else {
            // Update existing user
            users.put(user.id(), user);
            return user;
        }
    }
    
    @Override
    public Optional<User> findById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
        
        return Optional.ofNullable(users.get(id));
    }
    
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
    
    @Override
    public void deleteById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
        
        users.remove(id);
    }
}

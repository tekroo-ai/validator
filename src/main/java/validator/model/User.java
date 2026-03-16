package validator.model;

/**
 * User record representing a user entity.
 * 
 * @param id the unique identifier for the user
 * @param name the user's name
 * @param email the user's email address
 */
public record User(long id, String name, String email) {
}

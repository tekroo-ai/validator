package validator.controller;

import io.javalin.http.Context;
import validator.exception.FieldValidationException;
import validator.model.User;
import validator.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for user operations.
 */
public class UserController {
    
    private final UserService userService;
    
    /**
     * Creates a new UserController with the given service.
     * 
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Creates a new user from JSON body.
     * 
     * @param ctx the Javalin context
     */
    public void createUser(Context ctx) {
        try {
            Map<String, Object> requestBody = ctx.bodyAsClass(Map.class);
            String name = (String) requestBody.get("name");
            String email = (String) requestBody.get("email");
            
            User user = userService.createUser(name, email);
            ctx.status(201).json(user);
            
        } catch (FieldValidationException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            ctx.status(400).json(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid request");
            ctx.status(400).json(errorResponse);
        }
    }
    
    /**
     * Gets a user by ID.
     * 
     * @param ctx the Javalin context
     */
    public void getUserById(Context ctx) {
        try {
            String idParam = ctx.pathParam("id");
            long id = Long.parseLong(idParam);
            
            if (id <= 0) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Invalid user ID");
                ctx.status(400).json(errorResponse);
                return;
            }
            
            Optional<User> user = userService.findById(id);
            
            if (user.isPresent()) {
                ctx.status(200).json(user.get());
            } else {
                ctx.status(404);
            }
            
        } catch (NumberFormatException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid user ID format");
            ctx.status(400).json(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Server error");
            ctx.status(500).json(errorResponse);
        }
    }
    
    /**
     * Gets all users.
     * 
     * @param ctx the Javalin context
     */
    public void getAllUsers(Context ctx) {
        try {
            List<User> users = userService.listAll();
            ctx.status(200).json(users);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Server error");
            ctx.status(500).json(errorResponse);
        }
    }
}

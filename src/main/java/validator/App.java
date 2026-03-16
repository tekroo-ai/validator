package validator;

import io.javalin.Javalin;
import validator.controller.UserController;
import validator.repository.JdbiUserRepository;
import validator.service.UserService;

/** Entry point. */
public class App {
    public static void main(String[] args) {
        // Create dependencies
        JdbiUserRepository userRepository = new JdbiUserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);
        
        // Create and configure Javalin server
        Javalin app = Javalin.create().start(7070);
        
        // Register routes
        app.post("/users", userController::createUser);
        app.get("/users/{id}", userController::getUserById);
        app.get("/users", userController::getAllUsers);
    }
}

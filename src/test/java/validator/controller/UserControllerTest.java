package validator.controller;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validator.controller.UserController;
import validator.repository.JdbiUserRepository;
import validator.service.UserService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for UserController with full Javalin server startup.
 */
class UserControllerTest {
    
    private static Javalin app;
    private static int port;
    private static HttpClient httpClient;
    private static JdbiUserRepository userRepository;
    private static UserService userService;
    
    @BeforeAll
    static void setUp() {
        // Create fresh H2 in-memory database components
        userRepository = new JdbiUserRepository();
        userService = new UserService(userRepository);
        UserController userController = new UserController(userService);
        
        // Create and configure Javalin server with Jackson JSON support
        app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson());
        });
        
        app.post("/users", userController::createUser);
        app.get("/users/{id}", userController::getUserById);
        app.get("/users", userController::getAllUsers);
        
        app.start(0);
        port = app.port();
        
        // Create HTTP client
        httpClient = HttpClient.newHttpClient();
        
        // Add shutdown hook to properly close server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (app != null) {
                app.stop();
            }
        }));
    }
    
    @AfterAll
    static void tearDown() {
        if (app != null) {
            app.stop();
        }
    }
    
    @BeforeEach
    void resetDatabase() {
        // Clear all users from repository to reset state
        userRepository.findAll().forEach(user -> userRepository.deleteById(user.id()));
    }
    
    @Test
    void postUsersWithValidNameAndEmailReturnsHttp201() throws Exception {
        String jsonBody = "{\"name\":\"John Doe\",\"email\":\"john@example.com\"}";
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/users"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(201, response.statusCode());
        String responseBody = response.body();
        assertTrue(responseBody.contains("\"name\":\"John Doe\""));
        assertTrue(responseBody.contains("\"email\":\"john@example.com\""));
        assertTrue(responseBody.contains("\"id\":"));
    }
    
    @Test
    void postUsersWithInvalidEmailReturnsHttp400() throws Exception {
        String jsonBody = "{\"name\":\"John Doe\",\"email\":\"invalid-email\"}";
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/users"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(400, response.statusCode());
        String responseBody = response.body();
        assertTrue(responseBody.contains("error"));
    }
    
    @Test
    void getUsersByIdForExistingUserReturnsHttp200WithCorrectJson() throws Exception {
        // First create a user
        String createJsonBody = "{\"name\":\"Jane Doe\",\"email\":\"jane@example.com\"}";
        HttpRequest createRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/users"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(createJsonBody))
            .build();
        
        HttpResponse<String> createResponse = httpClient.send(createRequest, HttpResponse.BodyHandlers.ofString());
        assertEquals(201, createResponse.statusCode());
        
        // Extract user ID from response (simple parsing)
        String createResponseBody = createResponse.body();
        int idStart = createResponseBody.indexOf("\"id\":") + 5;
        int idEnd = createResponseBody.indexOf(",", idStart);
        if (idEnd == -1) {
            idEnd = createResponseBody.indexOf("}", idStart);
        }
        String userId = createResponseBody.substring(idStart, idEnd);
        
        // Now get the user by ID
        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/users/" + userId))
            .GET()
            .build();
        
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(200, getResponse.statusCode());
        String getResponseBody = getResponse.body();
        assertTrue(getResponseBody.contains("\"name\":\"Jane Doe\""));
        assertTrue(getResponseBody.contains("\"email\":\"jane@example.com\""));
        assertTrue(getResponseBody.contains("\"id\":" + userId));
    }
    
    @Test
    void getUsersByIdForNonExistentIdReturnsHttp404() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/users/999"))
            .GET()
            .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(404, response.statusCode());
    }
    
    @Test
    void getUsersReturnsHttp200WithJsonArrayContainingAllCreatedUsers() throws Exception {
        // Create two users
        String user1Json = "{\"name\":\"Alice Smith\",\"email\":\"alice@example.com\"}";
        String user2Json = "{\"name\":\"Bob Jones\",\"email\":\"bob@example.com\"}";
        
        HttpRequest createRequest1 = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/users"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(user1Json))
            .build();
        
        HttpRequest createRequest2 = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/users"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(user2Json))
            .build();
        
        httpClient.send(createRequest1, HttpResponse.BodyHandlers.ofString());
        httpClient.send(createRequest2, HttpResponse.BodyHandlers.ofString());
        
        // Get all users
        HttpRequest getAllRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/users"))
            .GET()
            .build();
        
        HttpResponse<String> getAllResponse = httpClient.send(getAllRequest, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(200, getAllResponse.statusCode());
        String getAllResponseBody = getAllResponse.body();
        assertTrue(getAllResponseBody.startsWith("["));
        assertTrue(getAllResponseBody.endsWith("]"));
        assertTrue(getAllResponseBody.contains("\"name\":\"Alice Smith\""));
        assertTrue(getAllResponseBody.contains("\"email\":\"alice@example.com\""));
        assertTrue(getAllResponseBody.contains("\"name\":\"Bob Jones\""));
        assertTrue(getAllResponseBody.contains("\"email\":\"bob@example.com\""));
    }
}

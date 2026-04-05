import java.util.ArrayList;
import java.util.List;

public class UserManager {
    // Using a List instead of a Map to store users
    private List<User> userDatabase;
    private User currentUser;

    public UserManager() {
        this.userDatabase = new ArrayList<>();
        this.currentUser = null;
    }

    // Handles user registration
    public boolean registerUser(String id, String name, String surname, String email, String password, String department) {
        // First, check if a user with this email already exists
        if (searchUserByEmail(email) != null) {
            return false; // Registration fails: Email already exists
        }
        
        // If not found, create and add the new user
        User newUser = new User(id, name, surname, email, password, department);
        userDatabase.add(newUser);
        return true;
    }

    // Handles login authentication
    public boolean login(String email, String password) {
        User user = searchUserByEmail(email);
        
        // Check if user exists and passwords match
        if (user != null && user.getPassword().equals(password)) {
            this.currentUser = user;
            return true; // Login successful
        }
        return false; // Login failed
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // Searching users by iterating through the ArrayList
    public User searchUserByEmail(String email) {
        for (User user : userDatabase) {
            if (user.getEmail().equals(email)) {
                return user; // Found the user
            }
        }
        return null; // User not found after checking the whole list
    }
}
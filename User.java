import java.util.ArrayList;
import java.util.List;

public class User {
    
    // Basic Profile Information
    private String userId; 
    private String name;
    private String surname;
    private String email;
    private String password;
    private String department;

    // Lists for relationships, interests, and events
    private List<String> interests;
    private List<Integer> userInterestCodes;
    private List<User> friends;
    private List<FriendRequest> friendRequests;
    private List<HangoutRequest> eventHistory;
    private List<HangoutRequest> organizedEvents; // Required for the Organized Events page
    
    // User's schedule
    private Schedule schedule;

    // Predefined interest arrays
    public final int[] availableInterestCodes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    public final String[] availableInterestNames = {"Books", "Music", "Baking", "Volleyball", "Football", "Movies", "Comic Books", "Drawing", "Video Games", "Cat Person", "Dog Person", "Basketball", "Mentoring"};

    // Constructor (Matches the call in UserManager perfectly)
    public User(String name, String surname, String email, String password, String department) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.department = department;
        
        // Initialize lists to prevent NullPointerExceptions
        this.interests = new ArrayList<>();
        this.userInterestCodes = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
        this.eventHistory = new ArrayList<>();
        this.organizedEvents = new ArrayList<>();
        this.schedule = new Schedule();
    }

    //  Profile Management Methods 
    
    public void updateProfile(String newName, String newSurname, String newDepartment) {
        this.name = newName;
        this.surname = newSurname;
        this.department = newDepartment;
    }

    //  Adder Methods 

    public void addOrganizedEvent(HangoutRequest event) {
        if (!this.organizedEvents.contains(event)) {
            this.organizedEvents.add(event);
        }
    }

    public void addInterest(String interest) {
        
        if (!this.interests.contains(interest)) {
            this.interests.add(interest);
        }
    }

    public void addFriend(User newFriend) { 
        if (!this.friends.contains(newFriend)) {
            this.friends.add(newFriend); 
        }
    }

    public void addPendingRequest(FriendRequest fr) { 
        this.friendRequests.add(fr); 
    }

    //  Remover Methods 

    public void removeFriend(User friend) { 
        this.friends.remove(friend); 
    }

    public void removeInterest(String interest) { 
        this.interests.remove(interest); 
    }

    //  Getters 

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getDepartment() { return department; }
        
    
    // Returns the full name (First Last)
    public String getUserName() { return this.name + " " + this.surname; }
    
    public Schedule getSchedule() { return schedule; }
    public List<String> getInterests() { return interests; }
    public List<Integer> getUserInterestCodes() { return userInterestCodes; }
    public List<User> getFriends() { return friends; }
    public List<FriendRequest> getFriendRequests() { return friendRequests; }
    public List<HangoutRequest> getEventHistory() { return eventHistory; }
    public List<HangoutRequest> getOrganizedEvents() { return organizedEvents; }

    //  Setters 
    
    public void setUserId(String userId) { this.userId = userId; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }

    //  Utility Methods 

    public void renderUser() {
        // Method that will render user info
        System.out.println("Rendering user: " + getUserName());
    }
}

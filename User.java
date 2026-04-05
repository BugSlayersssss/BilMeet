import java.util.ArrayList;

public class User {
    
    private String userName;
    private String userSurname;
    private Schedule schedule;
    private int userId;
    private String userPassword;
    private String userMail;
    private ArrayList<String> userInterests;
    private ArrayList<User> userFriends;
    private ArrayList<FriendRequest> friendRequests;
    private ArrayList<HangoutRequest> eventHistory;

    public final String[] interestNames = {"Books", "Music", "Baking", "Volleyball", "Football", "Movies", "Comic Books", "Drawing", "Video Games", "Cat Person", "Dog Person", "Basketball", "Mentoring"};

    public User(String userName, String userSurname, String userPassword, String userMail) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userId = (int)(Math.random()) * 9000 + 1000;
        this.userPassword = userPassword;
        this.userMail = userMail;
        this.userFriends = new ArrayList<User>();       //create an empty arraylist of friends for now
        this.friendRequests = new ArrayList<FriendRequest>();      //empty request list
        this.eventHistory = new ArrayList<HangoutRequest>();
    }

    //Adder methods
    public void addFriend(User newFriend) { userFriends.add(newFriend); }
    public void addInterest(String interest) {
        userInterests.add(interest);
    }
    public void addPendingRequest(FriendRequest fr) { friendRequests.add(fr); }

    //Remover methods
    public void removeFriend(User friend) { userFriends.remove(friend); }
    public void removeInterest(String interest) { userInterests.remove(interest); }

    //Setter methods for profile editing
    public void setUserName(String newName) { this.userName = newName; }
    public void setSchedule(Schedule schedule) {this.schedule = schedule;}

    //Getter methods
    public String getUserName() { return userName; }
    public String getUserSurname() { return userSurname; }
    public int getUserId() { return userId; }
    public String getUserPassword() { return userPassword; }
    public String getUserMail() { return userMail; }
    public ArrayList<String> getUserInterests() { return userInterests; }
    public ArrayList<User> getUserFriends() { return userFriends; }
    public ArrayList<HangoutRequest> getUserEventHistory() { return eventHistory; }

}

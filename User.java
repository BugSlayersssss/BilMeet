import java.util.ArrayList;

public class User {
    
    private String userName;
    private String userPassword;
    private String userMail;
    private ArrayList<String> userInterests;
    private ArrayList<Integer> userInterestCodes;
    private ArrayList<User> userFriends;
    private ArrayList<FriendRequest> friendRequests;
    private ArrayList<HangoutRequest> eventHistory;

    public int[] interestCodes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    public String[] interestNames = {"Books", "Music", "Baking", "Volleyball", "Football", "Movies", "Comic Books", "Drawing", "Video Games", "Cat Person", "Dog Person", "Basketball", "Mentoring"};

    public User(String userName, String userPassword, String userMail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userMail = userMail;
        this.userInterests = new ArrayList<String>();  //create an empty arraylist of interests for now
        this.userInterestCodes = new ArrayList<Integer>();  //create an empty arraylist of interests for now
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

    //Setter methods
    public void setUserName(String newName) { this.userName = newName; }

    //Getter methods
    public String getUserName() { return userName; }
    public String getUserPassword() { return userPassword; }
    public String getUserMail() { return userMail; }
    public ArrayList<String> getUserInterests() { return userInterests; }
    public ArrayList<User> getUserFriends() { return userFriends; }

    public User testUser() {
        return new User("A", "123", "a@ug.bilkent.edu.tr");
    }

    public void renderUser() {

        //method that will render user info
    }

}

import java.util.ArrayList;

public class User {
    
    private String userName;
    private String userPassword;
    private String userMail;
    private ArrayList<String> userInterests;
    private ArrayList<User> userFriends;

    public User(String userName, String userPassword, String userMail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userMail = userMail;
        this.userInterests = new ArrayList<String>();  //create an empty arraylist of interests for now
        this.userFriends = new ArrayList<User>();      //create an empty arraylist of friends for now
    }

    //Adder methods
    public void addFriend(User newFriend) { userFriends.add(newFriend); }
    public void addInterest(String interest) { userInterests.add(interest); }

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

}

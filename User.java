import java.util.ArrayList;

public class User {
    
    private String userName;
    private String userPassword;
    private String userMail;
    private ArrayList<String> userInterests;

    public User(String userName, String userPassword, String userMail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userMail = userMail;
        userInterests = new ArrayList<String>();  //create an empty arraylist of interests for now
    }


    //Setter methods
    public void setUserName(String newName) { this.userName = newName; }

    //Getter methods
    public String getUserName() { return userName; }
    public String getUserPassword() { return userPassword; }
    public String getUserMail() { return userMail; }
    public ArrayList<String> getUserInterests() { return userInterests; }

}

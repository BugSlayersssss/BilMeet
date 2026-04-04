import java.util.ArrayList;

public class FriendManager {


    public FriendRequest sendRequest(User sender, User receiver) {
        receiver.addPendingRequest(new FriendRequest(sender));
        return null; //silence compiler
    }

    public void acceptRequest (FriendRequest request) { request.accept(); }

    public void rejectRequest (FriendRequest request) { request.reject(); }

    public ArrayList<User> getFriends (User user) {

        return user.getUserFriends();
    }
}

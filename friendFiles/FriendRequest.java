public class FriendRequest {
    
    private String status;
    private User senderr;

    public FriendRequest(User sender) {
        // awaiting, accepted, rejected
        status = "awaiting";
        senderr = sender;
    }

    public void accept() { status = "accepted"; }

    public void reject() { status = "rejected"; }

    public String getStatus() { return status; }

    public User getSender() { return senderr; }
}

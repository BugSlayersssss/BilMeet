import java.util.List;

public class Chat {
    private String chatID;
    private String chatType;
    private List<User> users;
    private List<Message> messages;
    public Chat (String chatID, String chatType, List<User> users){
        this.chatID= chatID;
        this.chatType= chatType;
        this.users= users;
    }
    public void addMessage(Message message){
        messages.add(message);
    }
}

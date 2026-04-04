import java.util.ArrayList;
import java.util.List;
public class Chat {
    private static int chatIdCounter = 1;
    private boolean isPrivate;
    private String chatId;
    private List<User> participants;
    private List<Message> messages;
    public Chat (boolean isPrivate){
        this.chatId="C"+ chatIdCounter++;
        this.isPrivate= isPrivate;
        this.participants = new ArrayList<>();
        this.messages= new ArrayList<>();
    }
    public void addMessage(Message message){
        this.messages.add(message);
    }
    public void addParticipants(User user){
        if(!participants.contains(user)){
            participants.add(user);
        }
    }
    public String getChatId(){
        return this.chatId;
    }
    public List<Message> getMessages(){
        return this.messages;
    }
     public List<User> getParticipants(){
        return this.participants;
    }
}

import java.util.List;
import java.util.ArrayList;
public class ChatManager {
    private List<Chat> allChats;
    
    public ChatManager(){
        this.allChats = new ArrayList<>();
    }
    
    
    public Chat createPrivateChat(User user1, User user2){
        Chat newChat = new Chat(true);
        newChat.addParticipants(user1);
        newChat.addParticipants(user2);
        allChats.add(newChat);
        return newChat;

    }
    public Chat createGroupChat(List<User> eventParticipants){
        Chat newChat = new Chat(false);
       for (User user: eventParticipants){
        newChat.addParticipants(user);
       }
       allChats.add(newChat);
       return newChat;
    }
    public void sendMessage(Chat chat, String content, User sender){
    if (chat != null && chat.getParticipants().contains(sender)) {
            Message newMessage = new Message(content, sender);
            chat.addMessage(newMessage);
        }
    }
    public List<Chat> getChatsForUser(User user){
        List<Chat> userChats= new ArrayList<>();
        for (Chat chat: allChats){
            if (chat.getParticipants().contains(user)){
                userChats.add(chat);
            }
        }
        return userChats;
    }   
}

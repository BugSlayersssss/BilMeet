import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
public class ChatManager {
    private List<Chat> chats;
    private int chatNum=0;
    
    
    public Chat createPrivateChat(User user1, User user2){
        List<User> users= new List<User>();
        users.add(user1);
        users.add(user2);
        chatNum++;
        return new Chat(chatNum, "private", users);

    }
    public Chat createGroupChat(HangoutRequest event){
        chatNum++;
        return new Chat(chatNum, "group", event.getParticipants());

    }
    public Message sendMessage(Chat chat, User sender, String text){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24-hour format
        int minute = calendar.get(Calendar.MINUTE);
        String time = hour+"."+minute;
        chat.addMessage(new Message(text,time,sender));
    }
}

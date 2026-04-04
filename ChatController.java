import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import java.util.List;
public class ChatController {
    @FXML private ListView<String> chatListView;
    @FXML private TextArea messageDisplayArea;
    @FXML private TextField messageInputField;
    private ChatManager chatManager;
    private Chat currentChat;
    private UserManager userManager;
    public void setManagers(ChatManager chatManager, UserManager userManager){
        this.chatManager= chatManager;
        this.userManager= userManager;
        loadUserChats();
    }
    private void loadUserChats(){
     chatListView.getItems().clear();
     User currentUser = userManager.getCurrentUser();
     if(currentUser !=null){
        List<Chat> userChats= chatManager.getChatsForUser(currentUser);
        for (Chat chat : userChats){
            chatListView.getItems().add("Chat ID:"+chat.getChatId());
        }
     }
    }
    @FXML
    public void handleSelectChat(){
        int selectedIndex= chatListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >=0){
            User currentUser = userManager.getCurrentUser();
            currentChat = chatManager.getChatsForUser(currentUser).get(selectedIndex);
            refreshMessageDisplay();
        }
    }
    
    @FXML
    public void handleSendMessage(ActionEvent e){
        String content= messageInputField.getText();
        if(currentChat != null&& !content.trim().isEmpty()){
            chatManager.sendMessage(currentChat, content,userManager.getCurrentUser);
            messageInputField.clear();
            refreshMessageDisplay();
        }
    }
    private void refreshMessageDisplay(){
        if (currentChat !=null){
            messageDisplayArea.clear();
            for(Message msg: currentChat.getMessages()){
                String timeString = msg.getSentAt().getHour()+"."+msg.getSentAt().getMinute();
                messageDisplayArea.appendText("["+timeString+"]"+msg.getSender().getUserName()+":"+msg.getContent()+"\n");
            }
        }
    }

}

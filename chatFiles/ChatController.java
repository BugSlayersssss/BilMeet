import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import java.util.List;

public class ChatController {

    
    @FXML private ListView<String> chatListView;
    @FXML private ListView<Message> messageListView; 
    @FXML private TextField messageInputField;
    @FXML private TextField newChatEmailField;

    
    private ChatManager chatManager;
    private UserManager userManager;
    private Chat currentChat;

    
    public void setManagers(ChatManager chatManager, UserManager userManager) {
        this.chatManager = chatManager;
        this.userManager = userManager;
        
        messageListView.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> listView) {
                return new MessageCell(userManager.getCurrentUser());
            }
        });
        
        loadUserChats();
    }

    private void loadUserChats() {
        chatListView.getItems().clear();
        User currentUser = userManager.getCurrentUser();
        
        if (currentUser != null) {
            List<Chat> userChats = chatManager.getChatsForUser(currentUser);
            for (Chat chat : userChats) {
                chatListView.getItems().add("Chat ID: " + chat.getChatId());
            }
        }
    }

    @FXML
    public void handleSelectChat() {
        int selectedIndex = chatListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            User currentUser = userManager.getCurrentUser();
            currentChat = chatManager.getChatsForUser(currentUser).get(selectedIndex);
            refreshMessageDisplay();
        }
    }

    @FXML
    public void handleStartNewChat(ActionEvent event) {
        String targetEmail = newChatEmailField.getText().trim();
        User targetUser = userManager.searchUserByEmail(targetEmail);
        User currentUser = userManager.getCurrentUser();

        
        if (targetUser != null && !targetUser.equals(currentUser)) {
            Chat newChat = chatManager.createPrivateChat(currentUser, targetUser);
            newChatEmailField.clear();
            
            loadUserChats(); 
            
            
            this.currentChat = newChat;
            chatListView.getSelectionModel().select("Chat ID: " + newChat.getChatId());
            refreshMessageDisplay();
        } else {
            
            System.out.println("Error: User not found, or you tried to chat with yourself!");
        }
    }

    @FXML
    public void handleSendMessage(ActionEvent event) {
        String content = messageInputField.getText();
        
        if (currentChat == null) {
            return; 
        }
        
        if (!content.trim().isEmpty()) {
            chatManager.sendMessage(currentChat, content, userManager.getCurrentUser());
            messageInputField.clear();
            refreshMessageDisplay();
        }
    }

    private void refreshMessageDisplay() {
        if (currentChat != null) {
            messageListView.setItems(FXCollections.observableArrayList(currentChat.getMessages()));
            messageListView.scrollTo(currentChat.getMessages().size() - 1);
        }
    }
}

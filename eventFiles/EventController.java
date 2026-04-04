import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.Node;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventController {

    @FXML private VBox eventListContainer; 
    @FXML private TextField eventNameInput;
    @FXML private TextField locationInput;
    @FXML private TextField startHourInput;
    @FXML private TextField startMinInput;
    @FXML private TextField endHourInput;
    @FXML private TextField endMinInput;
    
    @FXML private CheckBox cbSports;
    @FXML private CheckBox cbStudy;
    @FXML private CheckBox cbChatting;
    @FXML private CheckBox cbVideoGames;
    @FXML private CheckBox cbShopping;
    @FXML private CheckBox cbCafe;
    @FXML private CheckBox cbWatchParty;
    @FXML private CheckBox cbPicnic;

    @FXML
    public void loadEvents(ActionEvent e) {
        if (eventListContainer != null) {
            eventListContainer.getChildren().clear();
        }
        
        List<HangoutRequest> events = EventManager.getInstance().getAllEvents();
        if (events == null) return; 

        try {
            for (HangoutRequest request : events) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("event.fxml"));
                Node eventCard = loader.load();

                EventCardController cardController = loader.getController();
                cardController.setData(request);

                eventListContainer.getChildren().add(eventCard);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void handleCreateEvent(ActionEvent e) {
        String name = eventNameInput.getText();
        String location = locationInput.getText();

        int startHour, startMin, endHour, endMin;
        try {
            startHour = Integer.parseInt(startHourInput.getText().trim());
            startMin = Integer.parseInt(startMinInput.getText().trim());
            endHour = Integer.parseInt(endHourInput.getText().trim());
            endMin = Integer.parseInt(endMinInput.getText().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Just enter numbers for time fields!");
            return; 
        }

        List<String> selectedTags = new ArrayList<>();
        if (cbSports != null && cbSports.isSelected()) selectedTags.add("SPORTS");
        if (cbStudy != null && cbStudy.isSelected()) selectedTags.add("STUDY");
        if (cbChatting != null && cbChatting.isSelected()) selectedTags.add("CHATTING");
        if (cbVideoGames != null && cbVideoGames.isSelected()) selectedTags.add("VIDEO GAMES");
        if (cbShopping != null && cbShopping.isSelected()) selectedTags.add("SHOPPING");
        if (cbCafe != null && cbCafe.isSelected()) selectedTags.add("CAFE");
        if (cbWatchParty != null && cbWatchParty.isSelected()) selectedTags.add("WATCH PARTY");
        if (cbPicnic != null && cbPicnic.isSelected()) selectedTags.add("PICNIC");

        List<User> participants = new ArrayList<>();

        HangoutRequest newEvent = new HangoutRequest(
            name, 
            location, 
            startHour, 
            startMin, 
            endHour, 
            endMin, 
            selectedTags, 
            participants, 
            LoginController.getCurrentUser()
        );
        
        EventManager.getInstance().addEvent(newEvent);
        
        eventNameInput.clear();
        locationInput.clear();
        startHourInput.clear();
        startMinInput.clear();
        endHourInput.clear();
        endMinInput.clear();
    }

    

    @FXML
    public void handleFilterByTag(ActionEvent e) {
        // Implementation for filtering
    }
}
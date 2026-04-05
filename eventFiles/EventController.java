import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventController {
    @FXML private VBox eventListContainer; 
    @FXML private TextField eventNameInput, locationInput, startHourInput, startMinInput, endHourInput, endMinInput, quotaInput;
    @FXML private CheckBox cbSports, cbStudy, cbChatting, cbVideoGames, cbShopping, cbCafe, cbWatchParty, cbPicnic;

    private static EventController instance;
    public UserManager userManager = new UserManager();
    public void initialize() { instance = this; loadEvents(); }
    public static EventController getInstance() { return instance; }

    
        @FXML
    public void loadEvents() {
        if (eventListContainer == null) return;
        eventListContainer.getChildren().clear();
        
        User me = userManager.getCurrentUser();
        Schedule mySchedule = me.getSchedule(); 

        List<HangoutRequest> recommended = EventManager.getInstance().getRecommendedEvents(me);
        List<HangoutRequest> expiredEventsToRemove = new ArrayList<>(); 

        try {
            for (HangoutRequest request : recommended) {

                if (request.isExpired()) {
                    expiredEventsToRemove.add(request); 
                    continue; 
                }
                int dayIndexForSchedule = request.getDay() - 1; 
                
                if (mySchedule != null && !mySchedule.isAvailableForEvent(dayIndexForSchedule, request.getStartHour(), request.getEndHour())) {
                    continue; 
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("event.fxml"));
                Node eventCard = loader.load();
                EventCardController cardController = loader.getController();
                cardController.setData(request);
                eventListContainer.getChildren().add(eventCard);
            }

            if (!expiredEventsToRemove.isEmpty()) {
                EventManager.getInstance().getAllEvents().removeAll(expiredEventsToRemove);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    public void replaceCardWithDetails(Node oldCard, HangoutRequest request) {
        try {
            int index = eventListContainer.getChildren().indexOf(oldCard);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("eventDetails.fxml"));
            Node detailedNode = loader.load();
            ((EventDetailsController)loader.getController()).loadDetailedData(request);
            eventListContainer.getChildren().set(index, detailedNode);
        } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    public void handleFilterByTag(ActionEvent e) {
        String tag = ((Button)e.getSource()).getText().trim();
        eventListContainer.getChildren().clear();
        renderList(EventManager.getInstance().handleFilterByTag(tag));
    }

    private void renderList(List<HangoutRequest> list) {
        try {
            for (HangoutRequest req : list) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("event.fxml"));
                Node card = loader.load();
                ((EventCardController)loader.getController()).setData(req);
                eventListContainer.getChildren().add(card);
            }
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    @FXML
    public void handleCreateEvent(ActionEvent e) {
        try {
            List<String> tags = new ArrayList<>();
            if (cbSports != null && cbSports.isSelected()) tags.add("SPORTS");
            if (cbStudy != null && cbStudy.isSelected()) tags.add("STUDY");     
            if (cbChatting != null && cbChatting.isSelected()) tags.add("CHATTING");    
            if (cbVideoGames != null && cbVideoGames.isSelected()) tags.add("VIDEO_GAMES");
            if (cbShopping != null && cbShopping.isSelected()) tags.add("SHOPPING");
            if (cbCafe != null && cbCafe.isSelected()) tags.add("CAFE");
            if (cbWatchParty != null && cbWatchParty.isSelected()) tags.add("WATCH_PARTY");
            if (cbPicnic != null && cbPicnic.isSelected()) tags.add("PICNIC");

            

            HangoutRequest newEvent = new HangoutRequest(eventNameInput.getText(), locationInput.getText(), 
                Integer.parseInt(startHourInput.getText().trim()), Integer.parseInt(startMinInput.getText().trim()),
                Integer.parseInt(endHourInput.getText().trim()), Integer.parseInt(endMinInput.getText().trim()), 
                tags, new ArrayList<>(), Integer.parseInt(quotaInput.getText().trim()), userManager.getCurrentUser());

            EventManager.getInstance().addEvent(newEvent);
            userManager.getCurrentUser().addOrganizedEvent(newEvent);
            loadEvents(); 
        } catch (Exception ex) { System.out.println("Input hatası!"); }
    }

    @FXML
    public void goToCreateEventScreen(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("createEvent.fxml"));
            ((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().setRoot(root);
        } catch (IOException e) { e.printStackTrace(); }
    }
}

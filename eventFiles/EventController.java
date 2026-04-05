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
    public void initialize() { instance = this; loadEvents(); }
    public static EventController getInstance() { return instance; }

    @FXML
    public void loadEvents() {
        if (eventListContainer == null) return;
        eventListContainer.getChildren().clear();
        User me = LoginManager.getCurrentUser();
        List<HangoutRequest> recommended = EventManager.getInstance().getRecommendedEvents(me);
        renderList(recommended);
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
            // ... diğer tagleri buraya ekle ...

            HangoutRequest newEvent = new HangoutRequest(eventNameInput.getText(), locationInput.getText(), 
                Integer.parseInt(startHourInput.getText().trim()), Integer.parseInt(startMinInput.getText().trim()),
                Integer.parseInt(endHourInput.getText().trim()), Integer.parseInt(endMinInput.getText().trim()), 
                tags, new ArrayList<>(), Integer.parseInt(quotaInput.getText().trim()), LoginManager.getCurrentUser());

            EventManager.getInstance().addEvent(newEvent);
            LoginManager.getCurrentUser().addOrganizedEvent(newEvent);
            loadEvents(); // Listeyi yenile
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

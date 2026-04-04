
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class EventDetailsController {

    @FXML private Label eventNameLabel;
    @FXML private Label timeLabel;
    @FXML private Label locationLabel;
    @FXML private Label tagsLabel;
    @FXML private Button organiserButton;
    @FXML private Button joinButton;

    private HangoutRequest currentEvent;

    public void loadDetailedData(HangoutRequest event) {
        this.currentEvent = event;
        eventNameLabel.setText(event.getName());
        timeLabel.setText(event.getStartTime() + " - " + event.getEndTime());
        locationLabel.setText(event.getLocation());
        tagsLabel.setText(String.join(", ", event.getTags()));
        
        if (event.getOrganiser() != null) {
            organiserButton.setText(event.getOrganiser().getName());
        }
    }

    @FXML
    public void handleShowOrganiserProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            Parent root = loader.load();
            ProfileController profileController = loader.getController();
            profileController.setUser(currentEvent.getOrganiser());
            Stage stage = (Stage) organiserButton.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleJoinEvent(ActionEvent event) {
        User currentUser = SessionManager.getCurrentUser();
        EventManager.getInstance().handleJoinEvent(currentEvent, currentUser);
        joinButton.setDisable(true);
        joinButton.setText("Joined");
    }

    @FXML
    public void handleIgnoreRequest(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void handleBack(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
}

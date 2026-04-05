
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class EventDetailsController {
    @FXML private Text eventNameLabel, timeLabel, locationLabel, tagsLabel;
    @FXML private Button organiserButton, joinEventButton, ignoreButton;    
    private HangoutRequest currentEvent;

    public void loadDetailedData(HangoutRequest req) {
        this.currentEvent = req;
        eventNameLabel.setText(req.getName().toUpperCase());
        timeLabel.setText(req.getStartTime() + " - " + req.getEndTime());
        locationLabel.setText(req.getLocation());
        tagsLabel.setText(String.join(", ", req.getTags()));
        organiserButton.setText(req.getOrganiser().getName());
        
        User me = LoginManager.getCurrentUser();
        if (req.getOrganiser().equals(me)) setupAsOrganizer();
        else setupAsParticipant(me);
    }

    private void setupAsParticipant(User me) {
        if (currentEvent.contains(me)) { joinEventButton.setText("JOINED"); joinEventButton.setDisable(true); }
        else if (currentEvent.isFull()) { joinEventButton.setText("FULL"); joinEventButton.setDisable(true); }
        else { joinEventButton.setText("JOIN EVENT"); joinEventButton.setDisable(false); }
    }

    private void setupAsOrganizer() { joinEventButton.setText("DELETE"); if (ignoreButton != null) ignoreButton.setVisible(false); }

    @FXML public void handleJoinEvent(ActionEvent e) { 
        EventManager.getInstance().handleJoinEvent(currentEvent, LoginManager.getCurrentUser()); 
        setupAsParticipant(LoginManager.getCurrentUser()); 
    }

    @FXML public void handleBack(ActionEvent e) { EventController.getInstance().loadEvents(); }
    @FXML
public void handleShowOrganiserProfile(ActionEvent event) {
    try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("strangerProfile.fxml"));
        Parent root = loader.load();

        StrangerProfileController controller = loader.getController();
        controller.setUser(currentEvent.getOrganiser()); 

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

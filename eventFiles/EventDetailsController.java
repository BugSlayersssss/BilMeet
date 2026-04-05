
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
    UserManager userManager = new UserManager();

    public void loadDetailedData(HangoutRequest req) {
        this.currentEvent = req;
        eventNameLabel.setText(req.getName().toUpperCase());
        timeLabel.setText(req.getStartTime() + " - " + req.getEndTime());
        locationLabel.setText(req.getLocation());
        tagsLabel.setText(String.join(", ", req.getTags()));
        organiserButton.setText(req.getOrganiser().getName());
        
        User me = userManager.getCurrentUser();
        if (req.getOrganiser().equals(me)) setupAsOrganizer();
        else setupAsParticipant(me);
    }

    private void setupAsParticipant(User me) {
        if (currentEvent.contains(me)) { joinEventButton.setText("JOINED"); joinEventButton.setDisable(true); }
        else if (currentEvent.isFull()) { joinEventButton.setText("FULL"); joinEventButton.setDisable(true); }
        else { joinEventButton.setText("JOIN EVENT"); joinEventButton.setDisable(false); }
    }

    private void setupAsOrganizer() { joinEventButton.setText("DELETE"); if (ignoreButton != null) ignoreButton.setVisible(false); }

    private void setupButtonStatus() {
        User me = userManager.getCurrentUser();
        
        if (currentEvent.getOrganiser().equals(me)) {

            joinEventButton.setText("DELETE EVENT");
            joinEventButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-background-radius: 15;");
        } 
        else if (currentEvent.contains(me)) {

            joinEventButton.setText("LEAVE EVENT");
            joinEventButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white; -fx-background-radius: 15;");
        } 
        else if (currentEvent.isFull()) {

            joinEventButton.setText("FULL");
            joinEventButton.setDisable(true);
            joinEventButton.setStyle("-fx-background-color: #808080; -fx-text-fill: white; -fx-background-radius: 15;");
        } 
        else {
            joinEventButton.setText("JOIN EVENT");
            joinEventButton.setDisable(false);
            joinEventButton.setStyle("-fx-background-color: #1A365D; -fx-text-fill: white; -fx-background-radius: 15;");
        }
    }

    @FXML 
    public void handleJoinEvent(ActionEvent e) { 
        User me = userManager.getCurrentUser();
        
        if (currentEvent.getOrganiser().equals(me)) {

            EventManager.getInstance().getAllEvents().remove(currentEvent); 
            me.getOrganizedEvents().remove(currentEvent); 
            handleBack(e); 
        } 
        else if (currentEvent.contains(me)) {
            currentEvent.getParticipants().remove(me);
            me.getEventHistory().remove(currentEvent);
            handleBack(e); 
        }
        else {

            EventManager.getInstance().handleJoinEvent(currentEvent, me); 
            setupButtonStatus(); 
        }
    }

    @FXML public void handleBack(ActionEvent e) { EventController.getInstance().loadEvents(); }
    @FXML
    public void handleShowOrganiserProfile(ActionEvent event) {
        try {
            //this part needs editing because ıdk if we have a profile view for organiser or not, if we do we can show organiser's profile when click on organiser's name
            FXMLLoader loader = new FXMLLoader(getClass().getResource("strangerProfile.fxml"));
            Parent root = loader.load();

            profileViewController controller = loader.getController();
            controller.setUser(currentEvent.getOrganiser()); 

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}

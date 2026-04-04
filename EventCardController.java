import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;

public class EventCardController {

    @FXML private TextArea nameOfEvent;
    @FXML private TextArea timingOfEvent;
    @FXML private TextArea locationOfEvent;

    private HangoutRequest myEvent;

    public void setData(HangoutRequest request) {
        this.myEvent = request;
        nameOfEvent.setText(request.getName());
        timingOfEvent.setText(request.getStartTime() + " - " + request.getEndTime());
        locationOfEvent.setText(request.getLocation());
        
        nameOfEvent.setEditable(false);
        timingOfEvent.setEditable(false);
        locationOfEvent.setEditable(false);
    }

    @FXML
    public void handleShowDetails(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("eventDetails.fxml"));
            Parent root = loader.load();

            EventDetailsController detailsController = loader.getController();
            detailsController.loadDetailedData(myEvent);

            Stage detailStage = new Stage();
            detailStage.setTitle("Event Details - " + myEvent.getName());
            detailStage.setScene(new Scene(root));
            detailStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
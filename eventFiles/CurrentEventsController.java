import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrentEventsController {

    @FXML private VBox currentEventsContainer;
    private static CurrentEventsController instance;
    UserManager userManager = new UserManager();    
    @FXML
    public void initialize() {
        instance = this;
        loadCurrentEvents();
    }

    public static CurrentEventsController getInstance() {
        return instance;
    }

    public void loadCurrentEvents() {
        if (currentEventsContainer == null) return;
        currentEventsContainer.getChildren().clear();

        User me = userManager.getCurrentUser();
        List<HangoutRequest> activeEvents = new ArrayList<>();

        for (HangoutRequest req : me.getOrganizedEvents()) {
            if (!req.isExpired() && !activeEvents.contains(req)) {
                activeEvents.add(req);
            }
        }
        for (HangoutRequest req : me.getEventHistory()) {
            if (!req.isExpired() && !activeEvents.contains(req)) {
                activeEvents.add(req);
            }
        }

        try {
            for (HangoutRequest event : activeEvents) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("event.fxml"));
                Node eventCard = loader.load();
                EventCardController cardController = loader.getController();
                cardController.setData(event);
                currentEventsContainer.getChildren().add(eventCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replaceCardWithDetails(Node oldCard, HangoutRequest request) {
        try {
            int index = currentEventsContainer.getChildren().indexOf(oldCard);
            if (index == -1) return;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("eventDetails.fxml"));
            Node detailedNode = loader.load();
            EventDetailsController controller = loader.getController();
            controller.loadDetailedData(request);

            currentEventsContainer.getChildren().set(index, detailedNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hideDetails() {
        loadCurrentEvents();
    }

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

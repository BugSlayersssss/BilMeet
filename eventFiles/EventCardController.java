import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class EventCardController {


    @FXML private Text nameOfEvent;    
    @FXML private Text timingOfEvent;  
    @FXML private Text locationOfEvent; 
    @FXML private Button details;       

    private HangoutRequest myEvent;

    
    public void setData(HangoutRequest request) {
        this.myEvent = request;
        
     
        if (request.getName() != null) {
            nameOfEvent.setText(request.getName().toUpperCase());
        }
        
        timingOfEvent.setText(request.getStartTime() + " - " + request.getEndTime());
        locationOfEvent.setText(request.getLocation());
    }

    @FXML
    public void handleShowDetails(ActionEvent event) {

        Node currentCardNode = (Node) ((Button)event.getSource()).getParent();
        
    
        EventController.getInstance().replaceCardWithDetails(currentCardNode, myEvent);
    }
}

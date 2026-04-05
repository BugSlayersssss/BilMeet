import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MessageCell extends ListCell<Message> {
    private HBox graphicContainer;
    private VBox bubbleLayout;
    private Label senderLabel;
    private Label contentLabel;
    private Label timeLabel;
    private User currentUser;

    public MessageCell(User currentUser) {
        this.currentUser = currentUser;
        
        // Initialize layout components
        graphicContainer = new HBox();
        bubbleLayout = new VBox(5);
        senderLabel = new Label();
        contentLabel = new Label();
        timeLabel = new Label();

        // Styling the text
        senderLabel.setFont(Font.font("System", FontWeight.BOLD, 10));
        senderLabel.setTextFill(javafx.scene.paint.Color.DARKGRAY);
        
        contentLabel.setWrapText(true);
        contentLabel.setMaxWidth(250); // Prevent bubbles from getting too wide
        contentLabel.setFont(Font.font("System", 14));
        
        timeLabel.setFont(Font.font("System", 9));
        timeLabel.setTextFill(javafx.scene.paint.Color.GRAY);

        // Styling the bubble layout
        bubbleLayout.setPadding(new Insets(8, 12, 8, 12));
        
        // Assemble the bubble
        bubbleLayout.getChildren().addAll(senderLabel, contentLabel, timeLabel);
        graphicContainer.getChildren().add(bubbleLayout);
    }

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);

        if (empty || message == null) {
            setText(null);
            setGraphic(null);
            setStyle("-fx-background-color: transparent;"); 
        } else {
            
            senderLabel.setText(message.getSender().getUserName());
            contentLabel.setText(message.getContent());
            
            
            String timeString = String.format("%02d:%02d", message.getSentAt().getHour(), message.getSentAt().getMinute());
            timeLabel.setText(timeString);
            timeLabel.setTextFill(javafx.scene.paint.Color.BLACK);

            
            if (message.getSender().equals(currentUser)) {
               
                graphicContainer.setAlignment(Pos.CENTER_RIGHT);
                bubbleLayout.setStyle("-fx-background-color: #1D74AB; -fx-background-radius: 15 15 0 15;");
                senderLabel.setVisible(true); 
                senderLabel.setManaged(true);
            } else {
                
                graphicContainer.setAlignment(Pos.CENTER_LEFT);
                bubbleLayout.setStyle("-fx-background-color: #6d6b6b; -fx-background-radius: 15 15 15 0;");
                senderLabel.setTextFill(javafx.scene.paint.Color.BLACK);
                senderLabel.setVisible(true);
                senderLabel.setManaged(true);
            }

            setGraphic(graphicContainer);
            setStyle("-fx-background-color: transparent;"); 
        }
    }
}
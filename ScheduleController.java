import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ScheduleController {

    @FXML
    private GridPane scheduleGrid;

    private boolean[][] busySlots = new boolean[7][24];
    private final String[] DAYS = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    
    private final String FREE_STYLE = "-fx-background-color: #A5D6A7; -fx-border-color: #81C784; -fx-border-radius: 3; -fx-background-radius: 3;";
    private final String BUSY_STYLE = "-fx-background-color: #EF9A9A; -fx-border-color: #E57373; -fx-border-radius: 3; -fx-background-radius: 3;";
    @FXML
    public void initialize() {
        buildGrid();
    }

    private void buildGrid() {
        
        for (int col = 0; col < 7; col++) {
            Label dayLabel = new Label(DAYS[col]);
            dayLabel.setFont(new Font("System Bold", 12));
            dayLabel.setPrefWidth(60);
            dayLabel.setAlignment(Pos.CENTER);
            scheduleGrid.add(dayLabel, col + 1, 0); 
        }

        
        for (int row = 0; row < 24; row++) {
            String timeText = String.format("%02d:00", row);
            Label timeLabel = new Label(timeText);
            timeLabel.setPrefWidth(50);
            timeLabel.setAlignment(Pos.CENTER_RIGHT);
            scheduleGrid.add(timeLabel, 0, row + 1);

            for (int col = 0; col < 7; col++) {
                Button slotButton = new Button();
                slotButton.setPrefSize(60, 25);
                slotButton.setStyle(FREE_STYLE); 
                final int finalCol = col;
                final int finalRow = row;
                slotButton.setOnAction(e -> handleSlotClick(finalCol, finalRow, slotButton));
                scheduleGrid.add(slotButton, col + 1, row + 1);
            }
        }
    }

    private void handleSlotClick(int dayIndex, int hourIndex, Button clickedButton) {
        busySlots[dayIndex][hourIndex] = !busySlots[dayIndex][hourIndex];
        if (busySlots[dayIndex][hourIndex]) {
            clickedButton.setStyle(BUSY_STYLE);
        } else {
            clickedButton.setStyle(FREE_STYLE);
        }
        
        System.out.println("Toggled " + DAYS[dayIndex] + " at " + hourIndex + ":00 to " + (busySlots[dayIndex][hourIndex] ? "BUSY" : "FREE"));
    }

    @FXML
    public void handleSaveSchedule(ActionEvent event) {
        System.out.println("Schedule saved successfully!");
    }
}
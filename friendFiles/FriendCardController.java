import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class FriendCardController {

    @FXML
    private TextArea nameArea;

    @FXML
    private TextArea surnameArea;

    @FXML
    private TextArea idArea;

    public void setData(User friend) {
        
        nameArea.setText(friend.getUserName());
        surnameArea.setText(friend.getUserSurname());
        idArea.setText(String.valueOf(friend.getUserId()));
    }
}
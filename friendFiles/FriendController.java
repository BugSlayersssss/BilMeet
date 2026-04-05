import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;

public class FriendController {

    @FXML
    private VBox friendsContainer;

    public void openFriends(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("friendView.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void sendFriendRequest() {

    }

    public void acceptFriendRequest (FriendRequest request) {

    }

    public void rejectFriendRequest (FriendRequest request) {

    }

    public void loadFriends (User user) {

        if (friendsContainer != null) {
            friendsContainer.getChildren().clear();
        }

        
        ArrayList<User> myFriends = user.getUserFriends();

        try {
            for (User friend : myFriends) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("friendObject.fxml"));  //gets the friend card
                Node card = loader.load();

                
                FriendCardController controller = loader.getController();
                controller.setData(friend);
                
                friendsContainer.getChildren().add(card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

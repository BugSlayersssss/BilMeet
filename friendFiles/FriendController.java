import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;

public class FriendController {

    public User u = new User("k", "a", "a"); //DELETE THIS LATER TODO

    public void openFriends(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("friendView.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void loadUsers() {
        //TODO
        //FOR THE SEARCH SCREEN IG?
    }

    public void sendFriendRequest() {

    }

    public void acceptFriendRequest (FriendRequest request) {

    }

    public void rejectFriendRequest (FriendRequest request) {

    }

    public void loadFriends (User user) {

        for (int i = 0; i < u.getUserFriends().size(); i++) {

            user.renderUser(); //TODO
            //Some kind of method that lists the user
        }
    }


}

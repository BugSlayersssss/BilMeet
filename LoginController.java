import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField mailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label wrongPasswordLabel;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String mail = mailField.getText();
        String password = passwordField.getText();

        if (mail.equals("test") && password.equals("1234")) {
            wrongPasswordLabel.setVisible(false);
            openProfile(event);
        } else {
            wrongPasswordLabel.setVisible(true);
        }
    }
    @FXML
    public void openProfile(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profileView.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void goToForgotPassword(ActionEvent event) throws IOException {

    Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
    }

    @FXML
    private void goToSignUp(ActionEvent event) throws IOException{
       
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
       
    }
}    

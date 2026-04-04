import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginNew.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("BilMeet");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
} 

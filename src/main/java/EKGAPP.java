import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EKGAPP extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("ekgGUI.fxml")));
        AnchorPane anchorPane = loader.load();
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();


    }
}

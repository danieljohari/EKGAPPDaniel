import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class LoginGUIController  extends Application {

    public Button loginKnap;
    public Label labelLæge;
    public TextField txtUsername;
    public PasswordField txtPassword;


    public void login(MouseEvent event) throws IOException {
        if (txtUsername.getText().equals("user") && txtPassword.getText().equals("pass")){

            Parent parLogin = FXMLLoader.load(getClass().getResource("ekgGUI.fxml"));
            Scene sceneLogin = new Scene(parLogin);
            Stage stageLogin = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageLogin.setScene(sceneLogin);
            stageLogin.show();
        } else {
            labelLæge.setText("Login Failed");
        }
    }

    public void start(Stage primaryStage) throws Exception {

    }
}

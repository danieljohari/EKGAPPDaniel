import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.ToggleButton;
import java.io.IOException;

public class LoginGUIController  extends Application {

    public Button loginKnap;
    public Label labelLæge;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public ToggleButton lægeKnap;
    public ToggleButton sygeKnap;
    public ToggleButton patientKnap;

//Knap til patient valg
    public void pKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {

            }
        });
    }
    //Knap til sygeplejerske valg
    public void sKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {

            }
        });
    }
    //Knap til læge valg
   public void lKnap(){
        Platform.runLater(new Runnable() {
            public void run() {

            }
        });
    }

//Login metoden.
    public void login(MouseEvent event) throws IOException {
        //Tjekker om conditions for brugernavn og password og lægeknap matcher (.equals) og (.isSelected())
        if (txtUsername.getText().equals("user") && txtPassword.getText().equals("pass") && lægeKnap.isSelected()){

        //Hvis det gør, skal det åbne læge GUI.
            Parent parLogin = FXMLLoader.load(getClass().getResource("lægeGUI.fxml"));
            Scene sceneLogin = new Scene(parLogin);
            Stage stageLogin = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageLogin.setScene(sceneLogin);
            stageLogin.show();
        }//Tjekker om conditions for brugernavn og password og sygeplejerskeknap matcher (.equals) og (.isSelected())

        if (txtUsername.getText().equals("syge") && txtPassword.getText().equals("pass") && sygeKnap.isSelected()) {
        //Hvis det gør, skal det åbne sygeplejerske GUI.
            Parent parLogin = FXMLLoader.load(getClass().getResource("sygeGUI.fxml"));
            Scene sceneLogin = new Scene(parLogin);
            Stage stageLogin = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageLogin.setScene(sceneLogin);
            stageLogin.show();

        } //Tjekker om conditions for brugernavn og password og patientknap matcher (.equals) og (.isSelected())
        if (txtUsername.getText().equals("patient") && txtPassword.getText().equals("pass") && patientKnap.isSelected()){

        //Hvis det gør, skal det åbne patient GUI.
            Parent parLogin = FXMLLoader.load(getClass().getResource("patientGUI.fxml"));
            Scene sceneLogin = new Scene(parLogin);
            Stage stageLogin = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageLogin.setScene(sceneLogin);
            stageLogin.show();
        }else {
                labelLæge.setText("Login Failed");
            }
        }


    public void start(Stage primaryStage) throws Exception {

    }
}



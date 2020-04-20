import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.management.monitor.Monitor;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class EKGAPP extends Application {

    /*
        private static SPO2Sim spo2Sim;
        private static BPMSim bpmSim;
        private static TempSim tempSim;

     */
    private static TempSim tempSim = new TempSim();
    private static BPMSim bpmSim = new BPMSim();
    private static SPO2Sim spo2Sim = new SPO2Sim();
    private static EkgGUIController ekgGUIController;
    private static FileWriter fw;
    private static BufferedWriter bw;
    private static PrintWriter pw;




    public static void main(String[] args) throws IOException, InterruptedException {
        fw = new FileWriter("ekgMålinger.txt", true);
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw, true);


        launch(args);


       pw.println("Temperatur: " + tempSim.getTemp() + " BPM: " + bpmSim.getBPM() + " SPO2: " + spo2Sim.getSPO2());

    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("ekgGUI.fxml")));
        AnchorPane anchorPane = loader.load();
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();





    /*
    public static double getLimit() {
        // public void getlimit() {
        boolean inputOK = true;
        double urgentMax;
       // while (inputOK) {
            try {
                Limit limit = new Limit();
                urgentMax = limit.askForUrgentTemp();
                System.out.println("Brugeren indtastede: " + urgentMax);
                inputOK = false;
            } catch (Exception e) {
                System.out.println("Skal være tal!");
            //}

        }
        return getLimit();
    }
*/


    }
}

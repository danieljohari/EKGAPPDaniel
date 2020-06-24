import com.sun.deploy.security.CertStore;
import data.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.sin;

public class patientGUIController implements  TempListener, SPO2Listener {

    //PRÆCIS SAMME SOM LÆGEGUICONTROLLER UDOVER DER IKKE ER GRÆNSEVÆRDI

    private TempSim tempSim = new TempSim();
    private SPO2Sim spo2Sim = new SPO2Sim();
    private double maxTemp =0.0;
    private double maxBPM = 0.0;
    private double maxSPO2 = 0.0;
    private String efternavn;
    private String fornavn;
    private String alder;
    private String kon;
    private PrintWriter pw;
    public TextField fornavnText;
    public TextField efternavnText;
    public TextField alderText;
    public Label konValgt;
    public Label bpmLabel;
    public Label tempLabel;
    public Label spo2Label;
    public Label konLabel;
    public Label offLabel;
    public Label warningTemp;
    public Label warningBPM;
    public Label warningSPO2;
    public Button manB;
    public Button kvindeB;
    public Button tempB;
    public Button bpmB;
    public Button spo2B;
    public Button bOn;
    public Button bOff;
    public Polyline polyline;
    private  BpmDAO bpmDAO = new BpmDAOSQLImpl();
    private TempDAO tempDAO = new TempDAOSQLImpl();
    private Spo2DAO spo2DAO = new Spo2DAOSQLImpl();


    public void ekgKnap(MouseEvent actionEvent) throws InterruptedException {

        //alt det her tager tid, og får sin egen tråd.
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    double y = 300 * Math.random()*sin(3.14*2 - 50);

                    polyline.getPoints().addAll(i * 10.0,y) ;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }
                }

        }).start();
    }

    public void tempKnap(MouseEvent mouseEvent) {
        TempSim tempSim = new TempSim();
        new Thread(tempSim).start();
        tempSim.register( this);

    }




    public void SPO2Knap(MouseEvent mouseEvent){
       SPO2Sim spo2Sim = new SPO2Sim();
       new Thread(spo2Sim).start();
       spo2Sim.register(this);
    }

    public void manKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                konValgt.setText("Mand");
            }
        });
    }

    public void kvindeKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                konValgt.setText("Kvinde");
            }
        });
    }

    public void onKnap(MouseEvent mouseEvent){
        Platform.runLater((new Runnable() {
            public void run() {
                offLabel.setText("Patient Monitoring System Online!");
                offLabel.setStyle("-fx-text-fill: green");
            }
        }));
    }



    public void offKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                offLabel.setText("Patient Monitoring System Off");
                offLabel.setStyle("-fx-text-fill: red");

            }
        });
    }

    public void granseKnap(MouseEvent mouseEvent ) {
        Limit lim = new Limit();
        maxTemp = lim.askForUrgentTemp();
        maxBPM = lim.askForUrgentBPM();
        maxSPO2 = lim.askForUrgentSPO2();
        Platform.runLater(new Runnable() {


            public void run() {

            }
        });

    }

    public void gemTextKnap (MouseEvent mouseEvent){


            this.fornavn = fornavnText.getText();
            this.efternavn = efternavnText.getText();
            this.alder = alderText.getText();
            this.kon = konValgt.getText();

            String dataPerson = fornavn +" " + efternavn + ", " + alder + ", " + kon;

                System.out.println(dataPerson);

            }




    public void notifybpm(BpmDTO bpm) {
        bpm.setCpr(fornavnText.getText());
        bpmDAO.save(bpm);
        //TODO: Save data i en database
        //DBController.save(bpm,fornavn, efternavn, alder, kon, new Date() );
        Platform.runLater(new Runnable() {
            public void run() {

                bpmLabel.setText("Patientens BPM: " + bpm );

                if (bpm.getBpm() >= maxBPM) {
                    warningBPM.setText("Advarsel! BPM over " + maxBPM);
                } else {
                    warningBPM.setText("");
                }


                }

        });

    }


    public void notifyTemp(TempDTO temp) {
        temp.setCpr(fornavnText.getText());
        tempDAO.save(temp);
        Platform.runLater(new Runnable() {
            public void run() {

                tempLabel.setText("Patientens Temperatur: " + temp);
                if (temp.getTemp() >= maxTemp){
                    warningTemp.setText("Advarsel! Temperatur over " + maxTemp);
                } else {
                    warningTemp.setText("");
                }
            }
        });
    }

    public void notifySpo2(Spo2DTO spo2) {
        spo2.setCpr(fornavnText.getText());
        spo2DAO.save(spo2);
        Platform.runLater(new Runnable() {
            public void run() {

                spo2Label.setText("Patientens SPO2: " + spo2);
                if( spo2.getSpo2() < maxSPO2) {
                    warningSPO2.setText("Advarsel! SPO2 under " + maxSPO2);
                } else {
                    warningSPO2.setText("");
                }
            }
        });
    }
    public void tilknap(final MouseEvent event){
        Platform.runLater(new Runnable() {
            public void run() {
                Parent parLogin = null;
                try {
                    parLogin = FXMLLoader.load(getClass().getResource("loginGUI.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene sceneLogin = new Scene(parLogin);
                Stage stageLogin = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageLogin.setScene(sceneLogin);
                stageLogin.show();
            }
        });
    }
}









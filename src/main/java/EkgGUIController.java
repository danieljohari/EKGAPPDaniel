import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import java.io.PrintWriter;
import static java.lang.Math.sin;

public class EkgGUIController {
    private TempSim tempSim = new TempSim();
    private  BPMSim bpmSim = new BPMSim();
    private SPO2Sim spo2Sim = new SPO2Sim();
    private double maxTemp =0.0;
    private double maxBPM = 0.0;
    private double maxSPO2 = 0.0;
    private  EKGAPP ekgapp;

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
    public EKGAPP getLimit;




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

    public void tempKnap() {
        //får sin egen thread.
        Platform.runLater(new Runnable() {
            public void run() {
                double currentTemp = 0;
                try {
                    currentTemp = tempSim.getTemp();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                tempLabel.setText("Patientens temperatur: " + currentTemp);
                if (currentTemp>= maxTemp){
                    warningTemp.setText("Advarsel! temperatur over "+maxTemp);
                } else if (currentTemp < maxTemp){
                    warningTemp.setText("");
                }

            }
        });
    }

    public void BPMKnap(MouseEvent mouseEvent) {
        Platform.runLater(new Runnable() {
            public void run() {

                double currentBPM = 0;
                try {
                    currentBPM = bpmSim.getBPM();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                bpmLabel.setText("Patientens BPM: " + currentBPM);


                if (currentBPM >= maxBPM) {
                    warningBPM.setText("Advarsel! puls over 120!");
                } else if (currentBPM < maxBPM) {
                    warningBPM.setText("");
                }

            }

        });
    }

    public void SPO2Knap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                double currentSPO2 = 0;
                try {
                    currentSPO2 = spo2Sim.getSPO2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                spo2Label.setText("Patientens SPO2: " +currentSPO2);


                    if (currentSPO2 <= maxSPO2) {
                        warningSPO2.setText("Advarsel! Iltmætning under 95%!");
                    } else if (currentSPO2 > maxSPO2){
                        warningSPO2.setText("");
                    }

            }
        });
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
                offLabel.setText("Patient Monitoring System Shutting down....");
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
        Platform.runLater(new Runnable() {
            public void run() {

            String fornavn = fornavnText.getText();
            String efternavn = efternavnText.getText();
            String alder = alderText.getText();
            String kon = konValgt.getText();

            String dataPerson = fornavn +" " + efternavn + ", " + alder + ", " + kon;

                System.out.println(dataPerson);



            }
        });
    }



}









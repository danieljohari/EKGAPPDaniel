import data.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import java.io.PrintWriter;

import static java.lang.Math.sin;

public class EkgGUIController implements BpmListener, TempListener, SPO2Listener {


    private TempSim tempSim = new TempSim();
    private BPMSim bpmSim = new BPMSim();
    private SPO2Sim spo2Sim = new SPO2Sim();
    private double maxTemp =0.0;
    private double maxBPM = 0.0;
    private double maxSPO2 = 0.0;
    private  EKGAPP ekgapp;
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
    public EKGAPP getLimit;
    private double bpm;
    private double temp;
    private double spo2;


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
        tempSim.register((TempListener) this);

    }

    public void BPMKnap(MouseEvent mouseEvent) {
        BPMSim bpmSim = new BPMSim();
        new Thread(bpmSim).start();
        bpmSim.register(this);
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


            this.fornavn = fornavnText.getText();
            this.efternavn = efternavnText.getText();
            this.alder = alderText.getText();
            this.kon = konValgt.getText();

            String dataPerson = fornavn +" " + efternavn + ", " + alder + ", " + kon;

                System.out.println(dataPerson);

            }




    public void notifybpm(final double bpm) {
        //TODO: Save data i en database
        //DBController.save(bpm,fornavn, efternavn, alder, kon, new Date() );
        Platform.runLater(new Runnable() {
            public void run() {

                bpmLabel.setText("Patientens BPM: " + bpm );

                if (bpm >= maxBPM) {
                    warningBPM.setText("Advarsel! BPM over " + maxBPM);
                } else {
                    warningBPM.setText("");
                }


                }

        });

    }


    public void notifyTemp(final double temp) {
        Platform.runLater(new Runnable() {
            public void run() {

                tempLabel.setText("Patientens Temperatur: " + temp);
                if (temp >= maxTemp){
                    warningTemp.setText("Advarsel! Temperatur over " + maxTemp);
                } else {
                    warningTemp.setText("");
                }
            }
        });
    }

    public void notifySpo2(final double spo2) {
        Platform.runLater(new Runnable() {
            public void run() {

                spo2Label.setText("Patientens SPO2: " + spo2);
                if( spo2 < maxSPO2) {
                    warningSPO2.setText("Advarsel! SPO2 under " + maxSPO2);
                } else {
                    warningSPO2.setText("");
                }
            }
        });
    }
}









import data.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.multiplyExact;
import static java.lang.Math.sin;

//Implementerer SIM interfaces
public class lægeGUIController implements BpmListener, TempListener, SPO2Listener, EkgListener {

    private double maxTemp =0.0;
    private double maxBPM = 0.0;
    private double maxSPO2 = 0.0;
    private String efternavn;
    private String fornavn;
    private String alder;
    private String kon;
    double x = 0.0;
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
    public LineChart lineChart;
    public TextField cprText;
    public Label ekgLabel;
    public Label ekgTekstData;
    private BpmDAO bpmDAO = new BpmDAOSQLImpl();
    private TempDAO tempDAO = new TempDAOSQLImpl();
    private Spo2DAO spo2DAO = new Spo2DAOSQLImpl();
    private EkgDAO ekgDAO = new EkgDAOSQLImpl();
    private ThreadEx threadEx = new ThreadEx();


    public void ekgKnap(MouseEvent actionEvent) {
        //alt det her tager tid, og får sin egen tråd.
        //EKG TEGNING VIA POLYLINE
        new Thread(new Runnable() {

            public void run() {

                    threadEx.t1.start();
                    threadEx.t2.start();
                    threadEx.t3.start();

                    try {
                        threadEx.t1.join();
                        threadEx.t2.join();
                        threadEx.t3.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                }


        }).start();
        threadEx.registerEkgListener(this);
    }

    //Temp knap, registerer temp fra class og interface
    public void tempKnap(MouseEvent mouseEvent) {
        TempSim tempSim = new TempSim();
        new Thread(tempSim).start();
        tempSim.register( this);
    }

    //bpm knap, registerer bpm fra class og interface
    public void BPMKnap(MouseEvent mouseEvent) {
        BPMSim bpmSim = new BPMSim();
        new Thread(bpmSim).start();
        bpmSim.register(this);
    }

    //spo2 knap, registerer spo2 fra class og interface
    public void SPO2Knap(MouseEvent mouseEvent){
       SPO2Sim spo2Sim = new SPO2Sim();
       new Thread(spo2Sim).start();
       spo2Sim.register(this);
    }



//Mandknap vælger køn
    public void manKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                konValgt.setText("Mand");
            }
        });
    }

//kvinde knap, vælger køn
    public void kvindeKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                konValgt.setText("Kvinde");
            }
        });
    }

//tænd knap
    public void onKnap(MouseEvent mouseEvent){
        Platform.runLater((new Runnable() {
            public void run() {
                offLabel.setText("Patient Monitoring System Online!");
                offLabel.setStyle("-fx-text-fill: green");
            }
        }));
    }

    //sluk knap
    public void offKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                offLabel.setText("Patient Monitoring System Off");
                offLabel.setStyle("-fx-text-fill: red");

            }
        });
    }

    //grænseknap til at sætte grænser
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

    //knap til at udskrive navn, efternavn, alder og køn i terminalen
    public void gemTextKnap (MouseEvent mouseEvent){

            this.fornavn = fornavnText.getText();
            this.efternavn = efternavnText.getText();
            this.alder = alderText.getText();
            this.kon = konValgt.getText();

            String dataPerson = fornavn +" " + efternavn + ", " + alder + ", " + kon;

                //System.out.println(dataPerson);

            }

//notify() metoden fra interface
    public void notifybpm(BpmDTO bpm) {
        //TODO: Save data i en database
        bpm.setCpr(cprText.getText());
        bpmDAO.save(bpm);
        //DBController.save(bpm,fornavn, efternavn, alder, kon, new Date() );
        Platform.runLater(new Runnable() {
            public void run() {

                bpmLabel.setText("Patientens BPM: " + bpm.getBpm() );

                //hvis bpm overstiger grænsen sat.
                if (bpm.getBpm() >= maxBPM) {
                    warningBPM.setText("Advarsel! BPM over " + maxBPM);
                } else {
                    warningBPM.setText("");
                }


                }
        });

    }

//samme som bpm
    public void notifyTemp(TempDTO temp) {
        temp.setCpr(cprText.getText());
        tempDAO.save(temp);
        Platform.runLater(new Runnable() {
            public void run() {

                tempLabel.setText("Patientens Temperatur: " + temp.getTemp());
                if (temp.getTemp() >= maxTemp){
                    warningTemp.setText("Advarsel! Temperatur over " + maxTemp);
                } else {
                    warningTemp.setText("");
                }
            }
        });
    }

    //samme som bpm
    public void notifySpo2(Spo2DTO spo2) {
        spo2.setCpr(cprText.getText());
        spo2DAO.save(spo2);
        Platform.runLater(new Runnable() {
            public void run() {

                spo2Label.setText("Patientens SPO2: " + spo2.getSpo2());
                if( spo2.getSpo2() < maxSPO2) {
                    warningSPO2.setText("Advarsel! SPO2 under " + maxSPO2);
                } else {
                    warningSPO2.setText("");
                }
            }
        });
    }

    //Tilbage knap til loginside
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


    public void notifyEkg(LinkedList<EKGDTO> ekgdtos) {


        //for (Integer i: ekgdtos) {
           // ekgDAO.savebatch(i);
           // System.out.println(ekgdtos);
            //ekgdto.setCpr(cprText.getText());
            //ekgDAO.savebatch((List<data.ekgdto>) listEKg.get(i));
           // System.out.println("RR:" + (List<data.ekgdto>) listEKg.get(i));
           // System.out.println(""+i);

            Platform.runLater(new Runnable() {
                public void run() {







                    lineChart.setTitle("EKG GRAF");


                    XYChart.Series series = new XYChart.Series();
                    series.setName("Vores EKG data");
                    Node line = series.getNode().lookup(".chart-series-area-line");


                    /*
                    series.getData().add(new XYChart.Data(1,23));
                    series.getData().add(new XYChart.Data(2,21));
                    series.getData().add(new XYChart.Data(3,26));
                    series.getData().add(new XYChart.Data(4,123));

                    lineChart.getData().add(series);
                    */

                    for (int i = 0; i < ekgdtos.size(); i++) {
                        XYChart.Data data = new XYChart.Data(x, ekgdtos.get(i).getEkg());
                        series.getData().add(data);
                        x++;
                        if (x > 600) {
                            //polyline.getPoints().setAll();
                            lineChart.getData().setAll();
                            x = 0;
                        }
                    }
                    lineChart.getData().add(series);






                    //polyline.getPoints().addAll(measuresPlot);


                ekgTekstData.setText(String.valueOf(ekgdtos.get(0).getEkg())); //Polyline tegn

                }
            });


        }



    }


//}









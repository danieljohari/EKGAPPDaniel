import data.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.LinkedList;
import java.util.List;


//Implementerer SIM interfaces
public class lægeGUIController implements TempListener, SPO2Listener, EkgListener {

    private double maxTemp = 0.0;
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
    XYChart.Series series = new XYChart.Series<>();
    List<EKGDTO> komuleretEkg = new LinkedList<>();
    public TextField cprText;
    public Label ekgLabel;
    public Label ekgTekstData;
    private BpmDAO bpmDAO = new BpmDAOSQLImpl();
    private TempDAO tempDAO = new TempDAOSQLImpl();
    private Spo2DAO spo2DAO = new Spo2DAOSQLImpl();
    private EkgDAO ekgDAO = new EkgDAOSQLImpl();
    private ThreadEx threadEx = new ThreadEx();
    Instant start = Instant.now();


    public void ekgKnap(MouseEvent actionEvent) {
        lineChart.setTitle("EKG GRAF");
        lineChart.getData().add(series);


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
        tempSim.register(this);
    }

    //bpm knap, registerer bpm fra class og interface

    //spo2 knap, registerer spo2 fra class og interface
    public void SPO2Knap(MouseEvent mouseEvent) {
        SPO2Sim spo2Sim = new SPO2Sim();
        new Thread(spo2Sim).start();
        spo2Sim.register(this);
    }


    //Mandknap vælger køn
    public void manKnap(MouseEvent mouseEvent) {
        Platform.runLater(new Runnable() {
            public void run() {
                konValgt.setText("Mand");
            }
        });
    }

    //kvinde knap, vælger køn
    public void kvindeKnap(MouseEvent mouseEvent) {
        Platform.runLater(new Runnable() {
            public void run() {
                konValgt.setText("Kvinde");
            }
        });
    }

    //tænd knap
    public void onKnap(MouseEvent mouseEvent) {
        Platform.runLater((new Runnable() {
            public void run() {
                offLabel.setText("Patient Monitoring System Online!");
                offLabel.setStyle("-fx-text-fill: green");
            }
        }));
    }

    //sluk knap
    public void offKnap(MouseEvent mouseEvent) {
        Platform.runLater(new Runnable() {
            public void run() {
                offLabel.setText("Patient Monitoring System Off");
                offLabel.setStyle("-fx-text-fill: red");

            }
        });
    }

    //grænseknap til at sætte grænser
    public void granseKnap(MouseEvent mouseEvent) {
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
    public void gemTextKnap(MouseEvent mouseEvent) {

        this.fornavn = fornavnText.getText();
        this.efternavn = efternavnText.getText();
        this.alder = alderText.getText();
        this.kon = konValgt.getText();

        String dataPerson = fornavn + " " + efternavn + ", " + alder + ", " + kon;

        //System.out.println(dataPerson);

    }

    //notify() metoden fra interface



    //samme som bpm
    public void notifyTemp(TempDTO temp) {
        temp.setCpr(cprText.getText());
        tempDAO.save(temp);
        Platform.runLater(new Runnable() {
            public void run() {

                tempLabel.setText("Patientens Temperatur: " + temp.getTemp());
                if (temp.getTemp() >= maxTemp) {
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
                if (spo2.getSpo2() < maxSPO2) {
                    warningSPO2.setText("Advarsel! SPO2 under " + maxSPO2);
                } else {
                    warningSPO2.setText("");
                }
            }
        });
    }

    //Tilbage knap til loginside
    public void tilknap(final MouseEvent event) {
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

    @Override
    public void notifyEkg(LinkedList<EKGDTO> ekgdtos) {
        // final double[] min = {Double.MIN_VALUE};
        komuleretEkg.addAll(ekgdtos);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                final int Size = 1000;
                lineChart.setCreateSymbols(false);
                lineChart.setAnimated(false);
                series.setName("Vores EKG data");

                for (int i = 0; i < ekgdtos.size(); i++) {
                    if (ekgdtos.get(i).getEkg() < 300) { //læst midt i strengen
                        // series.getData().remove(0);

                    } else {
                        series.getData().add(new XYChart.Data<>(x, ekgdtos.get(i).getEkg() - 300)); //Båndstop filter Lav

                        x++;
                    }
                }

                if (x > Size) {
                    List<EKGDTO> calcList = komuleretEkg;
                    calcBpm(calcList);
                    x = 0;
                    series.getData().clear();
                    komuleretEkg = new LinkedList<>();

                }

            }

        });
    }

        //beregn puls her:
private void calcBpm(List<EKGDTO> ekgdtoList) {
    new Thread(new Runnable() {
        @Override
        public void run() {
            double threshHold;
            double max = Double.MIN_VALUE;
            //double min = Double.MAX_VALUE;


            for (int i = 0; i < ekgdtoList.size(); i++) {
                if (ekgdtoList.get(i).getEkg() > max) {
                    max = ekgdtoList.get(i).getEkg();
                   // System.out.println("MAX LOOP" + max);

                }
                //System.out.println("EKG: "+ekgdtoList.get(i).getEkg());
            }
            threshHold = 0.7 * max;
            LinkedList<Timestamp> peakTimes = new LinkedList<>();
            for (EKGDTO ekg:ekgdtoList) {
                if (ekg.getEkg() > threshHold) {
                    if (peakTimes.size() == 0 || ekg.getTime().getTime() - peakTimes.getLast().getTime() > 25) {
                        peakTimes.add(ekg.getTime());
                    }
                }
            }
            long delta = peakTimes.getLast().getTime() - peakTimes.getFirst().getTime();
            long beatTime = delta / (peakTimes.size() - 1);
            double bpm = 60000.0/beatTime;
            BpmDTO bpmDTO = new BpmDTO();
            bpmDTO.setBpm(bpm);
            bpmDTO.setCpr(cprText.getText());
            bpmDTO.setTime(new Timestamp(System.currentTimeMillis()));
            Platform.runLater(() -> {
                bpmLabel.setText(String.valueOf("Patientens Bpm: " +(int) bpm));
                if (bpmDTO.getBpm() > maxBPM){
                    warningBPM.setText("Advarsel! BPM over" + maxBPM);
                } else {
                    warningBPM.setText(" ");
                }
            });

            new Thread(() -> {
                BpmDAOSQLImpl bpmDAOSQL = new BpmDAOSQLImpl();
                bpmDAOSQL.save(bpmDTO);
            }).start();

            //System.out.println("BMP: "+60000.0/beatTime);
            //System.out.println("AVG:" +threshHold);
            //System.out.println(peakTimes.size());
            //System.out.println("MAX:" +max);

        }

    }).start();
}


        @Override
        public void notifyEkgDb (LinkedList < EKGDTO > ekgdtoo) {

            Thread t4 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < ekgdtoo.size(); i++) {
                        ekgdtoo.get(i).setCpr(cprText.getText());
                    }
                    ekgDAO.savebatch(ekgdtoo);
                }
            });
            t4.start();
            try {
                t4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


}












import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;

public class EkgGUIController {
    private TempSim tempSim = new TempSim();
    private  BPMSim bpmSim = new BPMSim();
    private SPO2Sim spo2Sim = new SPO2Sim();
    public Label bpmLabel;
    public Label tempLabel;
    public Label spo2Label;
    public Button tempB;
    public Button bpmB;
    public Button spo2B;
    public Polyline polyline;


    public void ekgKnap(MouseEvent actionEvent) throws InterruptedException {

        //alt det her tager tid, og får sin egen tråd.
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    polyline.getPoints().addAll(i * 5.0, Math.random() * 100);
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
        //får sin egen thread.
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    tempLabel.setText("Patientens temperatur: " + tempSim.getTemp());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }

        });
    }


    public void BPMKnap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    bpmLabel.setText("Patientens BPM: " +bpmSim.getBPM());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void SPO2Knap(MouseEvent mouseEvent){
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    spo2Label.setText("Patientens SPO2: " +spo2Sim.getSPO2());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

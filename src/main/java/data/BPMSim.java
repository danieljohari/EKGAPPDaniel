package data;

import java.sql.Timestamp;

public class BPMSim implements Runnable, BpmSampler {

    //Implementerer en runnable (sin egen tråd), og bpmsampleren

    BpmListener bpmListener;
    //Erklærer BpmListener interface class
    public void register(BpmListener bpmListener){
        this.bpmListener = bpmListener;
    }
    //Registrerer "this = denne" bpmlistener, så den måling der er med det samme.

    double getBPM() {

        //metode til udregning af BPM

            int alder = 25;
            int maxBPM = 220 - alder;
            double targetHrRandom = Math.random() * maxBPM;
            double targetHrMin = 0.45 * maxBPM;

            double targetHR = (targetHrRandom + targetHrMin) / 2;
            targetHR = Math.round(targetHR);
            //System.out.println(targetHR); //test


            return targetHR;
        }

    public void run() {
        while (true) {
            try {
        // Hvis BPMlistener har en måling(værdi), skal den skrives ud.
                if (bpmListener != null) {
                    BpmDTO bpmDTO = new BpmDTO();
                    bpmDTO.setBpm(getBPM());
                    bpmDTO.setTime(new Timestamp(System.currentTimeMillis()));
                    bpmListener.notifybpm(bpmDTO);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}



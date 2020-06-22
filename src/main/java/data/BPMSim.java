package data;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class BPMSim implements Runnable, BpmSampler {

    //Implementerer en runnable (sin egen tråd), og bpmsampleren

    BpmListener bpmListener;
    //Erklærer BpmListener interface class
    public void register(BpmListener bpmListener){
        this.bpmListener = bpmListener;
    }

    //Registrerer "this = denne" bpmlistener, så den måling der er med det samme.

    double getBPM() {
/*
        //metode til udregning af BPM
       List<EKGDTO> bpmFraEKG = new LinkedList<>();
        //System.out.println(bpmFraEKG);
       int bcount = 0;
       int firstSlope = 0;
       int lastSlope = 0;
       double max = Double.MAX_VALUE;
       double min = Double.MIN_VALUE;

        for (int i = 0; i <bpmFraEKG.size() ; i++) {
            if (bpmFraEKG.get(i).getEkg() < min){
                min = bpmFraEKG.get(i).getEkg();
            }
            if (bpmFraEKG.get(i).getEkg() > max){
                max = bpmFraEKG.get(i).getEkg();
            }

        }
        double limit = 0.6*min + 0.4 *max;


        boolean first = false;
        for (int i = 1; i < bpmFraEKG.size() ; i++) {
            if (bpmFraEKG.get(i).getEkg() < limit && bpmFraEKG.get(i-1).getEkg() >= limit){
                if (!first){
                    firstSlope = i;
                    first = true;
                } else {
                    lastSlope = i;
                    bcount++;
                }
            }

        }
        double secElapsed = (lastSlope - firstSlope) * 0.025;
        double tidPrSlag = bcount / secElapsed;
        double targetHR = tidPrSlag * 60;
        System.out.println(targetHR);

 */


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



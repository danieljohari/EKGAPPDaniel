package data;

public class BPMSim implements Runnable, BpmSampler {

    BpmListener bpmListener;
    public void register(BpmListener bpmListener){
        this.bpmListener = bpmListener;
    }
    double getBPM() {


            int alder = 25;
            int maxBPM = 220 - alder;
            double targetHrRandom = Math.random() * maxBPM;
            double targetHrMin = 0.45 * maxBPM;




            double targetHR = (targetHrRandom + targetHrMin) / 2;
            targetHR = Math.round(targetHR);
            //System.out.println(targetHR); //test

            // double HR = Double.parseDouble(targetHR);


            return targetHR;
        }

    public void run() {
        while (true) {
            try {

                if (bpmListener != null) {
                    bpmListener.notifybpm(getBPM());
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}



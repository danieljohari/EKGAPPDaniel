package data;

public class SPO2Sim implements Runnable, SPO2Sampler {
    SPO2Listener spo2Listener;

    public void register(SPO2Listener spo2Listener) {
        this.spo2Listener = spo2Listener;
    }

    public double getSPO2()  {



            double R = Math.random() + 0.02; //RATIO
            double SPO2 = 110 - 25 * R; //en af formlerne
            SPO2 = Math.round(SPO2);
            //System.out.println(SPO2);

            return SPO2;


    }

    public void run() {
        while (true){
            try {
            if (spo2Listener != null){
                spo2Listener.notifySpo2(getSPO2());
            }

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

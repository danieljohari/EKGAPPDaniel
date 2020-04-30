package data;

import java.sql.Timestamp;

public class SPO2Sim implements Runnable, SPO2Sampler {
    SPO2Listener spo2Listener;

    public void register(SPO2Listener spo2Listener) {
        this.spo2Listener = spo2Listener;
    }

    public double getSPO2()  {

        //Metode til SPO2 SIM.

            double R = Math.random() + 0.02; //RATIO: SIMULERET OGSÅ
            double SPO2 = 110 - 25 * R; //en af formlerne
            SPO2 = Math.round(SPO2);
            //System.out.println(SPO2);

            return SPO2;

    }

    public void run() {
        while (true){
            try {
            if (spo2Listener != null){
                Spo2DTO spo2DTO = new Spo2DTO();
                spo2DTO.setSpo2(getSPO2());
                spo2DTO.setTime(new Timestamp(System.currentTimeMillis()));
                spo2Listener.notifySpo2(spo2DTO);
            }

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

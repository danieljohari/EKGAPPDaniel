package data;

import java.sql.Timestamp;

public class TempSim implements Runnable, TempSampler {

    TempListener tempListener;



    public void register(TempListener tempListener) {
        this.tempListener = tempListener;
    }

    double getTemp()  {

// TEMP SIM

            double value =  Math.random()*60 + 150;
            // tilfældigt tal, der ganges med 60 + 150 for at få en given værdi

            double temp = value * 4 / 50 + 24;
            // Given værdi fra ovenstående kode (math.random konverteres til grader celsius)
            temp = Math.round(temp * 100d / 100d);


            return temp;

    }

    public void run() {
        while (true){
            try {
            if (tempListener != null){
                TempDTO tempDTO = new TempDTO();
                tempDTO.setTemp(getTemp());
                tempDTO.setTime(new Timestamp(System.currentTimeMillis()));
                tempListener.notifyTemp(tempDTO);
            }

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}


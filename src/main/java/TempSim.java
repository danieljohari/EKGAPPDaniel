public class TempSim {
    double getTemp() throws InterruptedException {

        while(true) {
            Thread.sleep(100);
            double value =  Math.random()*60 + 150;
            // tilfældigt tal, der ganges med 60 + 150 for at få en given værdi

            double temp = value * 4 / 50 + 24;
            // Given værdi fra ovenstående kode (math.random konverteres til grader celsius)
            temp = Math.round(temp * 100d / 100d);


            return temp;
        }
    }
}


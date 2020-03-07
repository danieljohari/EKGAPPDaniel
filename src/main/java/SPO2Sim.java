public class SPO2Sim {
    double getSPO2() throws InterruptedException {

        while (true) {
            Thread.sleep(100);
            double R = Math.random() + 0.02; //RATIO
            double SPO2 = 110 - 25 * R; //en af formlerne
            SPO2 = Math.round(SPO2);
            //System.out.println(SPO2);

            return SPO2;
        }

    }
}

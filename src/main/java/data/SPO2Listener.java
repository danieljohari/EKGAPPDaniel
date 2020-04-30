package data;

// interface SPO2 simulatoren, med notify() metoden
//notify() metoden trækker en enkelt tråd ud, her er det SPO2
public interface SPO2Listener {
    public void notifySpo2(Spo2DTO spo2);
}

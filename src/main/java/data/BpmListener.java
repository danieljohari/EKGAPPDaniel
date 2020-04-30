package data;

// interface bpm simulatoren, med notify() metoden
//notify() metoden trækker en enkelt tråd ud, her er det bpm.
public interface BpmListener {
    public void notifybpm(BpmDTO bpm);


}

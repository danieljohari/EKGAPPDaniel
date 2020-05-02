package data;

// interface bpm simulatoren, med notify() metoden
// Bliver notified når der kommer ny måling.
public interface BpmListener {
    public void notifybpm(BpmDTO bpm);


}

package data;

// interface bpm simulatoren, med notify() metoden
// Bliver notified når der kommer ny måling.
// Ethvert objekt hvis tilstand kan være til interresse
// og hvor et andet objekt kan registrere denne interresse.
public interface BpmListener { //Subject
     void notifybpm(BpmDTO bpm);


}

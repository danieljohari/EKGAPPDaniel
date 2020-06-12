package data;

import java.sql.Timestamp;

public class BpmDTO { // DTO = DATA TRANSFER OBJECT - DVS fører data videre mellem processerne
    //Så herfra til sampler og listener og derfra til implSQL og videre til my SQL. Klassen indeholder metoderne til det.
    // Sender data et undersystem af appen til en anden.
    private double bpm;
    private String cpr;
    private Timestamp time;

    public double getBpm() {
        return bpm;
    }

    public void setBpm(double bpm) {
        this.bpm = bpm;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

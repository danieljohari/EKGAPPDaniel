package data;


import java.sql.Timestamp;

public class EKGDTO {
    private double ekg;
    private double bpm;
    private String cpr;
    private Timestamp time;

    public double getEkg() {
        return ekg;
    }

    public void setEkg(double ekg) {
        this.ekg = ekg;
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


    public double getBpm() {
        return bpm;
    }

    public void setBpm(double bpm) {
        this.bpm = bpm;
    }
}

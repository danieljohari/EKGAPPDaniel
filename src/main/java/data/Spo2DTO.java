package data;

import java.sql.Time;
import java.sql.Timestamp;

public class Spo2DTO {
    private double spo2;
    private String cpr;
    private Timestamp time;

    public double getSpo2() {
        return spo2;
    }

    public void setSpo2(double spo2) {
        this.spo2 = spo2;
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

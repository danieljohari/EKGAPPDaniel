package data;

import java.util.LinkedList;

public interface EkgListener {
    void notifyEkg(LinkedList<Integer> ekg);
   // void notifyPuls(PulsDTO pulsDTO);
}

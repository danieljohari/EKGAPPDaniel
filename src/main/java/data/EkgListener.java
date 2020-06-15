package data;

import java.util.LinkedList;

public interface EkgListener {
    void notifyEkg(LinkedList<Integer> ekgDTO);
   // void notifyPuls(PulsDTO pulsDTO);
}

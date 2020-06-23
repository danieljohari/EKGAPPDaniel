package data;

import java.util.LinkedList;

public interface EkgListener {
    void notifyEkg(LinkedList<EKGDTO> ekgDTO);
    void notifyEkgDb(LinkedList<EKGDTO> ekgdtoo);

}

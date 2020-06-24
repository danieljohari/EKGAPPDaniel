package data;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public interface EkgListener {
    void notifyEkg(LinkedList<EKGDTO> ekgDTO);
    void notifyEkgDb(LinkedList<EKGDTO> ekgdtoo);


}

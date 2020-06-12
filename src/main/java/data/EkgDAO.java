package data;

import java.util.LinkedList;
import java.util.List;

public interface EkgDAO {
   // void savebatch(List<EKGDTO> batch);

    List<EKGDTO> load(String cpr);

    void savebatch(LinkedList<Integer> batch);

}

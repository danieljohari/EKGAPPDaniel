
import data.EKGDTO;
import data.EkgListener;
import data.EkgSampler;
import java.util.LinkedList;
import java.util.List;

// This class has a list, producer (adds items to list
// and consumber (removes items).
public class PC implements EkgSampler {
   private SerialportConnector serialportConnector = new SerialportConnector(0);

    // Create a list shared by producer and consumer

    public LinkedList<EKGDTO> guiList = new LinkedList<>();
    public LinkedList<EKGDTO> dbList = new LinkedList<>();
    int capacity = 1000;
    private EkgListener listener;


    // Function called by producer thread
    public void produce() throws InterruptedException {

        while (true) {
            synchronized (this) {

                // producer thread waits while list
                // is full
                while (guiList.size() == capacity && dbList.size() == capacity)
                    wait();
                List<EKGDTO> value = serialportConnector.getData();

                if (value != null) {

                    for (EKGDTO i : value) {
                        guiList.add(i);
                        dbList.add(i);
                        //System.out.println(i);
                    }
                }

                // to insert the jobs in the list
                // notifies the consumer thread that
                // now it can start consumin
                notify();
            }
            Thread.sleep(2);
        }
    }

    // Function called by consumer thread
    public void guiConsume() throws InterruptedException {
        while (true) {
            LinkedList<EKGDTO> consumedList;
            synchronized (this) {
                // consumer thread waits while list
                // is empty

                while (guiList.size() < 5)
                    //System.out.println("waiting");
                    wait();

                // to retrive the ifrst job in the list
                consumedList = guiList;
                if ( listener != null){
                    listener.notifyEkg(consumedList);
                }
                guiList = new LinkedList<>();


                // Wake up producer thread
                notify();

                // and sleep

            }
           // System.out.println("Consumer consumed-");

        }
    }

    public void dbConsume() throws InterruptedException {
        while (true) {
            LinkedList<EKGDTO> consumedListDB;
            synchronized (this) {
                // consumer thread waits while list
                // is empty

                while (dbList.size() < 200)
                    //System.out.println("waiting");
                    wait();

                // to retrive the ifrst job in the list
                consumedListDB = dbList;
                if ( listener != null){
                    listener.notifyEkgDb(consumedListDB);
                }
                dbList = new LinkedList<>();

                // Wake up producer thread
                notify();

                // and sleep
            }
            //System.out.println("Consumer consumed- 2 DB CONSUMED");
        }
    }

    @Override
    public void register(EkgListener ekgListener) {
        this.listener = ekgListener;
    }
}



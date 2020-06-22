import data.EkgListener;

public class ThreadEx {
    final PC pc = new PC();

    // Create producer thread
    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });

    // Create consumer thread
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                pc.guiConsume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });
    Thread t3 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                pc.dbConsume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });


    public void registerEkgListener(EkgListener ekgListener) {
        pc.register(ekgListener);
    }
}









package base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DeadlockSimilator {
    // use cb to make sure we trigger a deadlock
    private Object a = new Object();
    private Object b = new Object();
    private CyclicBarrier cb = new CyclicBarrier(2);

    void run1() throws Exception {
        synchronized (a){
            cb.await();
            synchronized (b){

            }
        }
    }

    void run2()  throws Exception {
        synchronized (b){
            cb.await();
            synchronized (a){

            }
        }
    }

    public static void main(String[] args) {
        DeadlockSimilator ds = new DeadlockSimilator();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ds.run1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ds.run2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

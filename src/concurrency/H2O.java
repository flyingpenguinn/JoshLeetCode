package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class H2O {
    private int hcount = 2;

    private Semaphore hSem = new Semaphore(hcount, true);
    private Semaphore oSem = new Semaphore(0, true);// use fairness to give the earliest coming thread some priviledge

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        hSem.acquire(1);
        releaseHydrogen.run();
        oSem.release(1);
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        oSem.acquire(hcount);
        releaseOxygen.run();
        hSem.release(hcount);
    }
}

class H2oWaitNotify {
    public H2oWaitNotify() {

    }

    // can solve h3o2 in this way
    private int cur = 0;
    private int hs = 2;
    private int os = 1;
    private int n = hs + os;

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        synchronized (this) {
            while (cur % n >= hs) {
                wait();
            }
            releaseHydrogen.run();
            cur++;
            notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (this) {
            while (cur % n < hs) {
                wait();
            }
            releaseOxygen.run();
            cur++;
            notifyAll();
        }
    }

}
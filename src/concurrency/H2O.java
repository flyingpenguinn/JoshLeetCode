package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class H2O {

    ReentrantLock lock = new ReentrantLock();
    Condition oxyCondition = lock.newCondition();
    Condition hydroCondition = lock.newCondition();
    int hydro = 2;
    int oxy = 0;

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {


        lock.lock();
        try {
            while (hydro == 0) {
                hydroCondition.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hydro--;
            if (hydro == 0) {
                oxy = 1;
            }
            // must be signal all, not notify all!
            oxyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        lock.lock();
        try {
            while (oxy == 0) {
                oxyCondition.await();
            }
            releaseOxygen.run();
            oxy--;
            if (oxy == 0) {
                hydro = 2;
            }
            hydroCondition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

class H2oSemaphore {
    class H2O {
        Semaphore hydro = new Semaphore(2);
        Semaphore oxy = new Semaphore(0);

        public H2O() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            hydro.acquire();
            releaseHydrogen.run();
            oxy.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {

            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            oxy.acquire(2); // key: oxy to acquire 2
            releaseOxygen.run();
            hydro.release(2);
        }
    }
}
package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DesignBoundedBlockingQueue {
}

class BoundedBlockingQueue {

    private int[] q;
    private int head = 0;
    private int tail = -1;
    private int size = 0;
    private Lock lock = new ReentrantLock();
    private Condition notfull = lock.newCondition();
    private Condition notempty = lock.newCondition();

    public BoundedBlockingQueue(int n) {
        q = new int[n];
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        int n = q.length;
        try {
            while (size == n) {
                notfull.await();
            }
            int newtail = (tail + 1) % n;
            q[newtail] = element;
            tail = newtail;
            size++;
            notempty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        int n = q.length;
        try {
            while (size == 0) {
                notempty.await();
            }
            int v = q[head];
            int nh = (head + 1) % n;
            head = nh;
            size--;
            notfull.signalAll();
            return v;
        } finally {
            lock.unlock();
        }
    }

    // size needs lock as well for visibility!
    public int size() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }
}

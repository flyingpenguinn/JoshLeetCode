package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// the queue part is the same as "my circular deque"
class BoundedBlockingQueue {
    private int[] q = null;
    private int tail = 0;  // where to put next tail
    private int head = -1;  // where to put next head. real head is head +1 then mod
    private int cap = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private int size = 0;

    public BoundedBlockingQueue(int capacity) {
        cap = capacity;
        q = new int[cap];
        head = cap - 1;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (size == cap) {
                notFull.await();
            }
            q[tail] = element;
            tail = (tail + 1) % cap;
            size++;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (size == 0) {
                notEmpty.await();
            }
            head = (head + 1) % cap;
            int rt = q[head];
            size--;
            notFull.signalAll();
            return rt;
        } finally {
            lock.unlock();
        }

    }

    public int size() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }
}

class BoundedBlockingQueueSemaphore {
    // can use 3 semaphores to do the same job. note add/poll semaphores first then mutex
    class BoundedBlockingQueue {
        private int[] q = null;
        private int tail = 0;  // where to put next tail
        private int head = -1;  // where to put next head. real head is head +1 then mod
        private int cap = 0;
        private Semaphore mutex = null;
        private Semaphore addsem = null;
        private Semaphore pollsem = null;
        private int size = 0;

        public BoundedBlockingQueue(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("invalid size " + capacity);
            }
            cap = capacity;
            q = new int[cap];
            addsem = new Semaphore(capacity);
            pollsem = new Semaphore(0);
            mutex = new Semaphore(1);
        }

        public void enqueue(int element) throws InterruptedException {
            addsem.acquire();
            mutex.acquire();
            q[tail] = element;
            tail = (tail + 1) % cap;
            size++;
            mutex.release();
            pollsem.release();

        }

        public int dequeue() throws InterruptedException {
            pollsem.acquire();
            mutex.acquire();
            head = (head + 1) % cap;
            int rt = q[head];
            size--;
            mutex.release();
            addsem.release();
            return rt;
        }

        // size also needs lock!
        public int size() throws InterruptedException {
            mutex.acquire();
            int rt = this.size;
            mutex.release();
            return rt;
        }
    }
}
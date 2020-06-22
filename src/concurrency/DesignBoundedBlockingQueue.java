package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// the queue part is the same as "my circular deque"
class BoundedBlockingQueue {
    private ReentrantLock lock = new ReentrantLock();
    private int[] q;
    private int head = -1; // current head if we put in sth
    private int tail = 0; // current tail if we put in sth
    private int size = 0;
    private Condition notfull = lock.newCondition();
    private Condition notempty = lock.newCondition();
    private int capacity = 0;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        q = new int[capacity];
        this.head = capacity - 1;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (size == capacity) {
                notfull.await();
            }
            q[tail] = element;
            tail = (tail + 1) % capacity;
            size++;
            notempty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (size == 0) {
                notempty.await();
            }
            head = (head + 1) % capacity;
            int rt = q[head];
            size--;
            notfull.signalAll();
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
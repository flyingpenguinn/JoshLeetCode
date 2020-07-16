package crap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class MyConnectionPool {
    private static Random rand = new Random();


    public static void main(String[] args) {
        ConnectionPool cp = new ConnectionPool(5);
        for (int i = 0; i < 70; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MyConnection con = cp.getConnection();
                        System.out.println("got connection " + con.id + " used size " + cp.getUsedSize());
                        Thread.sleep(rand.nextInt(10000));
                        System.out.println("returning connection " + con.id + " used size " + cp.getUsedSize());
                        cp.returnConnection(con);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

class MyConnection {
    public int id = 0;

    public MyConnection(int id) {
        this.id = id;
    }

    public boolean isValid() {
        return true;
    }

    public void close() {

    }
}

class ConnectionPool {
    private ConcurrentLinkedQueue<MyConnection> pool = new ConcurrentLinkedQueue<>();
    private ConcurrentHashMap<MyConnection, Boolean> used = new ConcurrentHashMap<>();
    private Semaphore sem = null;

    public ConnectionPool(int size) {
        sem = new Semaphore(size);
        for (int i = 0; i < size; i++) {
            pool.offer(new MyConnection(ai.getAndIncrement()));
        }
    }

    private static AtomicInteger ai = new AtomicInteger();

    // must synchrotnize below...
    public MyConnection getConnection() throws InterruptedException {
        sem.acquire();
        MyConnection con = pool.poll();
        if (!con.isValid()) {
            con = new MyConnection(ai.getAndIncrement());
        }
        used.put(con, true);
        return con;
    }

    public void returnConnection(MyConnection con) {
        used.remove(con);
        pool.add(con);
        sem.release();
    }

    public void shutDown() {
        for (MyConnection con : used.keySet()) {
            con.close();
        }
        used.clear();
        pool.clear();
    }

    public synchronized int getUsedSize() {
        return used.size();

    }
}

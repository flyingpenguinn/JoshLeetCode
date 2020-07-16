package crap;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolJosh pool = new ThreadPoolJosh(10, 50);
        int count = 1000;
        final AtomicInteger counter = new AtomicInteger(1);

        for (int i = 0; i < count; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(counter.getAndIncrement() + " running thread " + Thread.currentThread().getName());
                }
            });

        }
        pool.shutDown();
    }
}

class ThreadPoolJosh {
    private BlockingQueue<FutureTask<?>> q = null;
    private volatile boolean done = false;
    private volatile boolean shutDown = false;
    private Thread[] threads = null;
    private Thread cleanup = new Thread(new Runnable() {

        @Override
        public void run() {
            while (true) {
                if (shutDown && q.isEmpty()) {
                    shutDownNow();
                    break;
                }
            }
        }
    });

    public ThreadPoolJosh(int qsize, int tcount) {
        q = new ArrayBlockingQueue<>(qsize);
        threads = new Thread[tcount];
        for (int i = 0; i < tcount; i++) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
        cleanup.setDaemon(true);
        cleanup.start();
    }

    public Future<Integer> submit(Runnable r) throws InterruptedException {
        if (shutDown || done) {
            System.out.println("already shut down");
            return null;
        }
        FutureTask<Integer> task = new FutureTask<>(r, 100); // some kind of good code. if put null here then type is void
        q.put(task);
        return task;
    }

    public <T> Future<T> submit(Callable<T> r) throws InterruptedException {
        if (shutDown || done) {
            throw new IllegalStateException();
        }
        FutureTask task = new FutureTask(r);
        q.put(task);
        return task;
    }

    public void shutDown() {
        shutDown = true;
    }


    public void shutDownNow() {
        done = true;
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            while (!done) {
                FutureTask task = null;
                try {
                    task = q.take();
                    task.run(); // future task will run and se the result
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("thread " + Thread.currentThread().getName() + " interrupted");
                } catch (Throwable e) {
                    System.out.println("got error " + e);
                }
            }
            System.out.println("thread " + Thread.currentThread().getName() + " ended");
        }
    }
}



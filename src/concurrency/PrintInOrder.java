package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class PrintInOrder {
}

class Foo {

    // could also use countdown latch as we only trigger them once
    public Foo() {

    }

    private Semaphore t1 = new Semaphore(1);
    private Semaphore t2 = new Semaphore(0);
    private Semaphore t3 = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        t1.acquire();
        printFirst.run();
        t2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        t2.acquire();
        printSecond.run();
        t3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        t3.acquire();
        printThird.run();
    }
}
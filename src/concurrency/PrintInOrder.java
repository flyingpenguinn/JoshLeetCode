package concurrency;

import java.util.concurrent.CountDownLatch;

public class PrintInOrder {
}


class Foo {

    // can also use semaphore
    CountDownLatch twoLatch = new CountDownLatch(1);
    CountDownLatch threeLatch = new CountDownLatch(1);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        twoLatch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        twoLatch.await();
        printSecond.run();
        threeLatch.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        threeLatch.await();
        printThird.run();
    }
}
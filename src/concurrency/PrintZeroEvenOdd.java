package concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {
    // similar to fizzbuzz!
}

class ZeroEvenOdd {
    // note we operate on n not 2*n here...
    private int n;

    private Semaphore zero = new Semaphore(1);
    private Semaphore odd = new Semaphore(0);
    private Semaphore even = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if (i % 2 == 1) {
                odd.release();
            } else {
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }
}

class ZeroEvenOddWaitNotify {

    private int n;

    public ZeroEvenOddWaitNotify(int n) {
        this.n = n;
    }

    private int cur = 0;

    private synchronized void commonPrint(int base, int mod, IntConsumer printNumber) throws InterruptedException {
        while (cur < 2 * n) {
            while (cur < 2 * n && cur % base != mod) { // must have this < check !
                wait();
            }
            if (cur >= 2 * n) {
                break;
            }
            printNumber.accept(base == 2 ? 0 : (cur + 1) / 2);
            cur++;
            notifyAll();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        commonPrint(2, 0, printNumber);
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        commonPrint(4, 3, printNumber);
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        commonPrint(4, 1, printNumber);
    }

}
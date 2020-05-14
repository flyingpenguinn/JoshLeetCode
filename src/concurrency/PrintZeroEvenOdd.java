package concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {
}

class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    private Semaphore zero = new Semaphore(1);
    private Semaphore odd = new Semaphore(0);
    private Semaphore even = new Semaphore(0);

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                odd.release();
            } else {
                even.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

        // (n+1)/2 because when n ==1 or 3 we print the odd but not the even
        for (int i = 0; i < (n + 1) / 2; i++) {
            odd.acquire();
            printNumber.accept(2 * i + 1);
            zero.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        for (int i = 0; i < n / 2; i++) {
            even.acquire();
            printNumber.accept(2 * i + 2);
            zero.release();
        }
    }
}

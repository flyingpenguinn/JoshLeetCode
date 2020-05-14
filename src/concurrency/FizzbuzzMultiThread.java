package concurrency;

import java.util.concurrent.*;
import java.util.function.IntConsumer;


class FizzBuzz {
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    Object lock = new Object();
    int cur = 1;

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (cur <= n && !(cur % 3 == 0 && cur % 5 != 0)) {
                    lock.wait();
                }
                if (cur > n) {
                    break;
                }
                printFizz.run();
                cur += 1;
                lock.notifyAll();
            }
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (cur <= n && !(cur % 5 == 0 && cur % 3 != 0)) {
                    lock.wait();
                }
                if (cur > n) {
                    break;
                }
                printBuzz.run();
                cur += 1;
                lock.notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (cur <= n && !(cur % 3 == 0 && cur % 5 == 0)) {
                    lock.wait();
                }
                if (cur > n) {
                    break;
                }
                printFizzBuzz.run();
                cur += 1;
                lock.notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (cur <= n && (cur % 3 == 0 || cur % 5 == 0)) {
                    lock.wait();
                }
                if (cur > n) {
                    break;
                }
                printNumber.accept(cur);
                cur += 1;
                lock.notifyAll();
            }
        }
    }
}


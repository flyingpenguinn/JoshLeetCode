package concurrency;

import java.util.concurrent.*;
import java.util.function.IntConsumer;


class FizzBuzz {
    private int n;
    private int cur = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized (this) {
            while (cur <= n) {
                while (cur <= n && !(cur % 3 == 0 && cur % 5 != 0)) {
                    wait();
                }
                if (cur > n) {
                    break;
                }
                printFizz.run();
                cur++;
                notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized (this) {
            while (cur <= n) {
                while (cur <= n && !(cur % 5 == 0 && cur % 3 != 0)) {
                    wait();
                }
                if (cur > n) {
                    break;
                }
                printBuzz.run();
                cur++;
                notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized (this) {
            while (cur <= n) {
                while (cur <= n && !(cur % 3 == 0 && cur % 5 == 0)) {
                    wait();
                }
                if (cur > n) {
                    break;
                }
                printFizzBuzz.run();
                cur++;
                notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            while (cur <= n) {
                while (cur <= n && (cur % 3 == 0 || cur % 5 == 0)) {
                    wait();
                }
                if (cur > n) {
                    break;
                }
                printNumber.accept(cur);
                cur++;
                notifyAll();
            }
        }
    }
}

class FizzBuzzSemaphore {
    private int n;

    private Semaphore sem3 = new Semaphore(0);
    private Semaphore sem5 = new Semaphore(0);
    private Semaphore sem15 = new Semaphore(0);
    private Semaphore semNum = new Semaphore(1);

    public FizzBuzzSemaphore(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 3 == 0 && i % 5 != 0) {
                sem3.acquire();
                printFizz.run();
                semNum.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 5 == 0 && i % 3 != 0) {
                sem5.acquire();
                printBuzz.run();
                semNum.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            if (i % 15 == 0) {
                sem15.acquire();
                printFizzBuzz.run();
                semNum.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semNum.acquire();
            if (i % 3 == 0 && i % 5 != 0) {
                sem3.release();
            } else if (i % 3 != 0 && i % 5 == 0) {
                sem5.release();
            } else if (i % 3 == 0 && i % 5 == 0) {
                sem15.release();
            } else {
                printNumber.accept(i);
                semNum.release();
            }
        }
    }
}


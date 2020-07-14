package concurrency;
import java.util.concurrent.Semaphore;

public class PrintFooBarAlternatively {
    class FooBar {
        private int n;
        private Semaphore fsem = new Semaphore(1);
        private Semaphore bsem = new Semaphore(0);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printFoo.run() outputs "foo". Do not change or remove this line.
                fsem.acquire();
                printFoo.run();
                bsem.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                bsem.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fsem.release();
            }
        }
    }
}

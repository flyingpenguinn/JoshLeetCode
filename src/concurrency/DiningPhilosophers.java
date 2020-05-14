package concurrency;

public class DiningPhilosophers {

    boolean[] occupied = new boolean[5];
    Object lock = new Object();

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int left = philosopher;
        int right = philosopher + 1 >= 5 ? 0 : philosopher + 1;
        synchronized (lock) {
            while (occupied[left] || occupied[right]) {
                lock.wait();
            }
            pickLeftFork.run();
            pickRightFork.run();
            occupied[left] = true;
            occupied[right] = true;
        }

        eat.run();
        synchronized (lock) {
            putLeftFork.run();
            putRightFork.run();
            occupied[left] = false;
            occupied[right] = false;
            lock.notifyAll();
        }
    }
}


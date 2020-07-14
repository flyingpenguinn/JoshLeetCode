package concurrency;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    // break the problem solution: even number picks left first, odd number picks right first: then a person either picks both or nothing
    // can also limit the number of philosophers to be 4 to avoid all 5 of them coming at the same time
    // somehow got wrong answer...
    Semaphore[] sems;

    public DiningPhilosophers() {
        this.sems = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            sems[i] = new Semaphore(1);
        }
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        Semaphore left = sems[philosopher];
        Semaphore right = sems[(philosopher + 1) % 5];

        if (philosopher % 2 == 0) {
            left.acquire();
            pickLeftFork.run();
            right.acquire();
            pickRightFork.run();
        } else {
            right.acquire();
            pickRightFork.run();
            left.acquire();
            pickLeftFork.run();
        }

        eat.run();

        if (philosopher % 2 == 0) {
            putRightFork.run();
            right.release();
            putLeftFork.run();
            left.release();
        } else {
            putLeftFork.run();
            left.release();
            putRightFork.run();
            right.release();
        }
    }
}


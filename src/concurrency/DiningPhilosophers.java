package concurrency;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    // break the problem solution: last person picks right first. others can still pick left first. note we only need 1 person to break the vicious cycle
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

        if (philosopher == 4) {
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

        if (philosopher == 4) {
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


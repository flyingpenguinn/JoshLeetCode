package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TrafficLight {
    Object lock = new Object();
    int state = 0;

    public TrafficLight() {

    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        synchronized (lock) {
            if ((direction - 1) / 2 == 0) {
                dowork(turnGreen, crossCar,0);
            }else{
                dowork(turnGreen, crossCar,1);
            }
        }
    }

    private void dowork(Runnable turnGreen, Runnable crossCar, int newstate) {
        if (state != newstate) {
            state = newstate;
            turnGreen.run();
        }
        crossCar.run();
    }
}

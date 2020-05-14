import java.util.Arrays;

public class CarFleet {

    class Car {
        int p;
        int s;
        double t;

        public Car(int p, int s, double t) {
            this.p = p;
            this.s = s;
            this.t = t;
        }
    }

    // cars behind me but arrive earlier form the same fleet with me
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) {
            return 0;
        }
        Car[] cars = new Car[n];

        for (int i = 0; i < n; i++) {
            Car c = new Car(position[i], speed[i], (target - position[i]) * 1.0 / speed[i]);
            cars[i] = c;
        }
        Arrays.sort(cars, (a, b) -> Integer.compare(b.p, a.p));
        double bt = cars[0].t;
        int ft = 1;
        for (int i = 1; i < n; i++) {
            if (cars[i].t > bt) {
                bt = cars[i].t;
                ft++;
            }
        }
        return ft;

    }
}

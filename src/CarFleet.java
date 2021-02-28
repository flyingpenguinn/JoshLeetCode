import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

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

class CarFleetStack {
    // bumper to bumper == use the same speed!
    // similar to carfleet2, use stack to keep cars slower than i, and scan backward...diff is here we have target so criteria changed
    // here we compare time to make sure we can catch before t. in fleet2 there was no t restriction but a clash restriction
    public int carFleet(int t, int[] p, int[] s) {
        int n = p.length;
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = p[i];
            cars[i][1] = s[i];
        }
        Arrays.sort(cars, (x, y) -> Integer.compare(x[0], y[0]));
        int res = 0;
        Deque<Integer> st = new ArrayDeque<>();
        double[] ts = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && cars[st.peek()][1] >= cars[i][1]) {
                st.pop();
            }
            while (!st.isEmpty()) {
                int j = st.peek();
                double time = (cars[j][0] - cars[i][0] + 0.0) / (cars[i][1] - cars[j][1]);
                // we caught j before its merge with others
                if (time <= ts[j]) {
                    ts[i] = time;
                    break;
                } else {
                    st.pop();
                }
            }
            if (st.isEmpty()) {
                ts[i] = (t - cars[i][0] + 0.0) / cars[i][1];
                res++;
            }
            st.push(i);
        }
        return res;
    }
}

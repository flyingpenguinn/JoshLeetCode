import java.util.TreeSet;

public class SeatReservationManager {
    class SeatManager {

        private TreeSet<Integer> ts = new TreeSet<>();

        public SeatManager(int n) {
            for (int i = 1; i <= n; i++) {
                ts.add(i);
            }
        }

        public int reserve() {
            int smallest = ts.first();
            ts.remove(smallest);
            return smallest;
        }

        public void unreserve(int seatNumber) {
            ts.add(seatNumber);
        }
    }
}

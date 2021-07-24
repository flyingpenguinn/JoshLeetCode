import java.util.*;

public class NumberOfSmallestUnoccupiedChair {
    // line sweep on arrival time
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] atime = new int[n][3];
        for (int i = 0; i < n; i++) {
            atime[i][0] = times[i][0];
            atime[i][1] = times[i][1];
            atime[i][2] = i;
        }
        int[][] ltime = new int[n][3];
        for (int i = 0; i < n; i++) {
            ltime[i][0] = times[i][0];
            ltime[i][1] = times[i][1];
            ltime[i][2] = i;
        }
        Arrays.sort(atime, (x, y) -> Integer.compare(x[0], y[0]));
        Arrays.sort(ltime, (x, y) -> Integer.compare(x[1], y[1]));
        int j = 0;
        TreeSet<Integer> avail = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            avail.add(i);
        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] time : atime) {
            int arrive = time[0];
            int ap = time[2];
            // calc leaving persons first!

            while (j < n && ltime[j][1] <= arrive) {
                int lp = ltime[j][2];
                int seat = m.get(lp);
                m.remove(lp);
                avail.add(seat);
                j++;
            }
            int seat = avail.first();
            if (ap == targetFriend) {
                return seat;
            }
            m.put(ap, seat);
            avail.remove(seat);
        }
        return -1;
    }
}

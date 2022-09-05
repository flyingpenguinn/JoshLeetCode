import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomIII {

    public int mostBooked(int n, int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        // must do this!
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> x[1] != y[1] ? Long.compare(x[1], y[1]) : Long.compare(x[0], y[0]));
        for (int i = 0; i < n; ++i) {
            pq.offer(new long[]{i, 0L});
        }
        int[] books = new int[n];
        PriorityQueue<long[]> pq2 = new PriorityQueue<>((x, y) -> Long.compare(x[0], y[0]));
        for (int i = 0; i < a.length; ++i) {
            long[] top = null;
            while (!pq.isEmpty() && pq.peek()[1] <= a[i][0]) {
                pq2.offer(pq.poll());
            }
            if (!pq2.isEmpty()) {
                top = pq2.poll();
            } else {
                top = pq.poll();
            }
            int gap = a[i][1] - a[i][0];
            long ctime = Math.max(a[i][0], top[1]);
            long croom = top[0];
            long cend = ctime + gap;
            ++books[(int) croom];
            pq.offer(new long[]{croom, cend});
        }
        int max = 0;
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, books[i]);
        }
        for (int i = 0; i < n; ++i) {
            if (books[i] == max) {
                return i;
            }
        }
        return -1;
    }
}

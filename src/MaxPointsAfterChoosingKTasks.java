import java.util.PriorityQueue;

public class MaxPointsAfterChoosingKTasks {
    public long maxPoints(int[] a, int[] b, int k) {
        int n = a.length;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long res = 0;
        long ck = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] >= b[i]) {
                res += a[i];
                ++ck;
            } else {
                res += b[i];
                long diff = b[i] - a[i];
                pq.offer(diff);
            }
        }
        while (ck < k) {
            res -= pq.poll();
            ++ck;
        }
        return res;
    }
}

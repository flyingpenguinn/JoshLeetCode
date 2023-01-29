import java.util.Collections;
import java.util.PriorityQueue;

public class PutMarblesIntoBags {
    public long putMarbles(int[] a, int k) {
        PriorityQueue<Long> pq1 = new PriorityQueue<>();
        PriorityQueue<Long> pq2 = new PriorityQueue<>(Collections.reverseOrder());
        long max = getvalue(a, pq1, k);
        long min = getvalue(a, pq2, k);
        return max - min;
    }

    private long getvalue(int[] a, PriorityQueue<Long> pq, int k) {
        int n = a.length;
        long psum = 0;
        for (int i = 0; i + 1 < n; ++i) {
            long sum = 0L + a[i] + a[i + 1];
            psum += sum;
            pq.offer(sum);
            if (pq.size() > k - 1) {
                psum -= pq.poll();
            }
        }
        return psum;
    }
}

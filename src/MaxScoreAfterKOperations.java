import java.util.Collections;
import java.util.PriorityQueue;

public class MaxScoreAfterKOperations {
    public long maxKelements(int[] a, int k) {
        int n = a.length;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int ai : a) {
            pq.offer(Long.valueOf(ai));
        }
        long res = 0;
        while (k > 0) {
            long top = pq.poll();
            res += top;
            long added = (long) Math.ceil(top * 1.0 / 3);
            pq.offer(added);
            --k;
        }
        return res;
    }
}

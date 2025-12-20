import java.util.Collections;
import java.util.PriorityQueue;

public class MaxSumAfterBinarySwaps {
    public long maximumScore(int[] a, String s) {
        int n = a.length;
        char[] c = s.toCharArray();

        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long res = 0;
        for (int i = 0; i < n; ++i) {
            pq.offer(Long.valueOf(a[i]));
            if (c[i] == '1') {
                res += pq.poll();
            }
        }
        return res;
    }
}

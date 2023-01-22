import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxSubsequenceScore {
    // fix one element in b as a "min" element, what are the possible elements to include in a? we take the biggest k out of them. so we sort b first
    public long maxScore(int[] a, int[] b, int k) {
        int n = a.length;
        int[][] cb = new int[n][2];
        for (int i = 0; i < n; ++i) {
            cb[i][0] = b[i];
            cb[i][1] = i;
        }
        Arrays.sort(cb, (x, y) -> Integer.compare(x[0], y[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long pqsum = 0L;
        for (int i = n - k + 1; i < n; ++i) {
            int index = cb[i][1];
            pq.offer(a[index]);
            pqsum += a[index];
        }
        long res = 0;
        for (int i = n - k; i >= 0; --i) {
            int bindex = cb[i][1];
            long sum1 = 1L * b[bindex] * a[bindex]; // the min element part
            long sum2 = 1L * pqsum * b[bindex]; // the rest of the elements
            long cur = sum1 + sum2;
            res = Math.max(cur, res);
            pq.offer(a[bindex]);
            pqsum += a[bindex];
            if (pq.size() > (k - 1)) { // min element is fixed, so just k-1 elements
                pqsum -= pq.poll();
            }
        }
        return res;
    }
}

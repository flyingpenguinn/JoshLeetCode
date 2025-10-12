import java.util.PriorityQueue;

public class MaxTransactionWithoutNegativeBalance {
    // similar to gas station question
    public int maxTransactions(int[] a) {
        int n = a.length;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long sum = 0;
        int res = n;
        for (int i = 0; i < n; ++i) {
            if (a[i] < 0) {
                pq.offer((long) a[i]);
            }
            sum += a[i];
            while (sum < 0 && !pq.isEmpty()) {
                sum -= pq.poll();
                --res;
            }
        }
        return res;
    }
}

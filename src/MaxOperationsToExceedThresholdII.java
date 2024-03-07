import java.util.PriorityQueue;

public class MaxOperationsToExceedThresholdII {
    public int minOperations(int[] a, int k) {
        int n = a.length;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            pq.offer(0L + a[i]);
        }
        int steps = 0;
        while (!pq.isEmpty() && pq.size() >= 2 && pq.peek() < k) {
            long v1 = pq.poll();
            long v2 = pq.poll();
            pq.offer(2 * v1 + v2);
            ++steps;
        }
        return steps;
    }
}

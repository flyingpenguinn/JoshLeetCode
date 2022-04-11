import java.util.PriorityQueue;

public class MinProductAfterKIncrements {
    private long Mod = (long) (1e9 + 7);

    public int maximumProduct(int[] a, int k) {
        int n = a.length;
        if (n == 1) {
            long res = 0L + a[0] + k;
            res %= Mod;
            return (int) res;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int ai : a) {
            pq.offer(ai);
        }
        while (!pq.isEmpty() && k > 0) {
            int v1 = pq.poll();
            int v2 = pq.peek();
            int delta = Math.min(k, v2 + 1 - v1);
            k -= delta;
            v1 += delta;
            pq.offer(v1);
        }
        long res = 1L;
        while (!pq.isEmpty()) {
            res *= pq.poll();
            res %= Mod;
        }
        return (int) res;
    }
}

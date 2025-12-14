import java.util.PriorityQueue;

public class MinMovesToBalanceCircularArray {
    public long minMoves(int[] a) {
        int n = a.length;
        int neg = -1;
        for (int i = 0; i < n; ++i) {
            if (a[i] < 0) {
                neg = i;
                break;
            }
        }
        if (neg == -1) {
            return 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(y[1], x[1]);
            }
        });
        for (int i = 0; i < n; ++i) {
            if (i == neg) {
                continue;
            }
            if (i > neg) {
                pq.offer(new int[]{Math.min(i - neg, n - i + neg), a[i], i});
            } else {
                pq.offer(new int[]{Math.min(neg - i, n - neg + i), a[i], i});
            }
        }
        long res = 0;
        while (a[neg] < 0 && !pq.isEmpty()) {
            int[] top = pq.poll();
            int index = top[2];
            long red = Math.min(-a[neg], a[index]);
            a[neg] += red;
            res += red * top[0];
        }
        return a[neg] < 0 ? -1 : res;
    }
}

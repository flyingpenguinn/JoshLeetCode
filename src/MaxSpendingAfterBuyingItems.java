import java.util.PriorityQueue;

public class MaxSpendingAfterBuyingItems {
    public long maxSpending(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[0], x[0]));
        for (int i = 0; i < m; ++i) {
            pq.offer(new int[]{a[i][0], i, 0});
        }
        long d = m * n;
        long res = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            long v = top[0];
            int ri = top[1];
            int ci = top[2];
            res += 1L * v * d;
            if (ci + 1 < a[ri].length) {
                pq.offer(new int[]{a[ri][ci + 1], ri, ci + 1});
            }
            --d;
        }
        return res;
    }
}

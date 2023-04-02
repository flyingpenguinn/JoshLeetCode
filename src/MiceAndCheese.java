import java.util.PriorityQueue;

public class MiceAndCheese {
    public int miceAndCheese(int[] a, int[] b, int k) {
        int n = a.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[0], x[0]));
        for (int i = 0; i < n; ++i) {
            int diff = a[i] - b[i];
            pq.offer(new int[]{diff, i});
        }
        int score = 0;
        boolean[] added = new boolean[n];
        while (!pq.isEmpty() && k > 0) {
            int[] top = pq.poll();
            int index = top[1];
            score += a[index];
            added[index] = true;
            --k;
        }
        for (int i = 0; i < n; ++i) {
            if (!added[i]) {
                score += b[i];
            }
        }
        return score;
    }
}

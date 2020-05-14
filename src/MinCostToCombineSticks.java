import java.util.PriorityQueue;

public class MinCostToCombineSticks {
    public int connectSticks(int[] a) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            pq.offer(a[i]);
        }
        int cost = 0;
        while (pq.size() >= 2) {
            int i1 = pq.poll();
            int i2 = pq.poll();
            cost += i1 + i2;

            pq.offer(i1 + i2);
        }
        return cost;
    }
}

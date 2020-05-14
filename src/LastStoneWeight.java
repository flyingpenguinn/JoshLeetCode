import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int s : stones) {
            pq.offer(s);
        }
        while (pq.size() > 1) {
            int s1 = pq.poll();
            int s2 = pq.poll();
            int ns = (Math.abs(s1 - s2));
            if (ns > 0) {
                pq.offer(ns);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}

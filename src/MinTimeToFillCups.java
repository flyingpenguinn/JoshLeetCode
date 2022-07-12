import java.util.Collections;
import java.util.PriorityQueue;

public class MinTimeToFillCups {
    public int fillCups(int[] a) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > 0) {
                pq.offer(a[i]);
            }
        }
        int res = 0;
        while (!pq.isEmpty()) {
            if (pq.size() == 1) {
                return res + pq.poll();
            } else {
                int v1 = pq.poll();
                int v2 = pq.poll();
                res += 1;
                if (v1 > 1) {
                    pq.offer(v1 - 1);
                }
                if (v2 > 1) {
                    pq.offer(v2 - 1);
                }
            }
        }
        return res;
    }
}

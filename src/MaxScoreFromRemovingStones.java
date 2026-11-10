import java.util.Collections;
import java.util.PriorityQueue;

public class MaxScoreFromRemovingStones {
    // dominant element: only same-pile conflict, so compare max vs the rest
    // if all is odd, one stone can't be paired
    public int maximumScore(int a, int b, int c) {
        int maxv = Math.max(a, Math.max(b, c));
        int all = a + b + c;
        if (2 * maxv <= all) {
            return all / 2;
        } else {
            int other = all - maxv;
            return other;
        }
    }
}

class MaxScoreFromRemovingStonesHeap {
    // take from the two biggest piles each time
    public int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(a);
        pq.offer(b);
        pq.offer(c);
        int res = 0;
        while (pq.size() >= 2) {
            int v1 = pq.poll();
            int v2 = pq.poll();
            if (pq.size() == 2) {
                res += v2;
                break;
            }
            res++;
            if (v1 > 1) {
                pq.offer(v1 - 1);
            }
            if (v2 > 1) {
                pq.offer(v2 - 1);
            }
        }
        return res;
    }
}
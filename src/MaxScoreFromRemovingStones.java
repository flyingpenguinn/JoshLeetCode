import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MaxScoreFromRemovingStones {
    // O(1)
    public int maximumScore(int a, int b, int c) {
        int[] na = new int[3];
        na[0] = a;
        na[1] = b;
        na[2] = c;
        Arrays.sort(na);
        if (na[0] + na[1] <= na[2]) {
            return na[0] + na[1];// pair each smaller ones with the biggest
        } else {
            return na[2] + (na[0] + na[1]-na[2]) / 2;// otherwise after above pairing, two smaller ones pair among themselves
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
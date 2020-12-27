import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class MaxNumberOfEatenAppels {
    // eat the apple that is rotting first. note as we move in the day we "enable" more apples
    public int eatenApples(int[] a, int[] d) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        int n = a.length;
        // eat rot firsts
        int maxd = 0;
        for (int i = 0; i < n; i++) {
            maxd = Math.max(maxd, i + d[i]);
        }
        int res = 0;
        for (int i = 0; i <= maxd; i++) {
            // rotten appels...
            while (!pq.isEmpty()) {
                if (pq.peek()[1] == i) {
                    pq.poll();
                } else {
                    break;
                }
            }
            // new apples
            if (i < n && a[i] > 0) {
                pq.offer(new int[]{a[i], i + d[i]});
            }
            // eaten appels
            if (!pq.isEmpty()) {
                res++;
                pq.peek()[0]--;
                if (pq.peek()[0] == 0) {
                    pq.poll();
                }
            }
        }
        return res;
    }
}

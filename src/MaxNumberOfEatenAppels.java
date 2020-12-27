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
            // rotten apples...
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

class MaxNumberOfEatenApplesIntervalMergeWrong {
    // submitted and ACed in contest, but actually wrong. can't pass [1,1]  [9,2]. accu for 0 is intertwined with that of 1
    // but is there a merge interval solution at all?
    public int eatenApples(int[] a, int[] d) {

        int[] apps = new int[200001];
        for (int i = 0; i < a.length; i++) {
            apps[i] += a[i];
            apps[i + d[i]] -= a[i];
        }
        int sum = 0;
        int res = 0;
        int accu = 0;// in this sprint how many did we eat- we will need to discount these when apps[i] is <0
        for (int i = 0; i < apps.length; i++) {
            if (apps[i] < 0) {
                sum += apps[i] + accu;
                accu = 0;
            } else {
                sum += apps[i];
            }
            if (sum - res > 0) {
                res++;
                // note that we ate apples so that when we negate we pay these back
                accu++;
            }
        }
        return res;
    }
}

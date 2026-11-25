import java.util.Collections;
import java.util.PriorityQueue;

public class MinOperationsToMakeMountainArrayII {
    // LC 4124 (new problem 2026-11-24). Coach-written reference solution, not Josh's: he got stuck on the large value
    // range and the slope trick is outside the current training scope (Exposed). Accepted on LC; brute-checked (303 cases).
    // Strictly increasing a[0..p] <=> b[i] = a[i] - i non-decreasing (M19). Same for the right side read right-to-left.
    // Min sum |change| to make a prefix non-decreasing = slope trick (CF 713C): the curve
    // dp[i][v] = |b_i - v| + min_{u <= v} dp[i-1][u] from 4118 is stored only by its breakpoints (a max-heap).
    // After a prefix, cost = minimum cost, heap top = the lowest possible final value of the last element.
    // Peak p: left part 0..p-1 and right part p+1..n-1 are independent; the peak can be raised freely,
    // so it must reach max(left last, right last) + 1: extra cost max(0, that - a[p]).
    public long minOperations(int[] a) {
        int n = a.length;
        if (n < 3) {
            return -1;
        }
        long[] lc = new long[n];
        long[] lx = new long[n];
        sweep(a, lc, lx, false);
        long[] rc = new long[n];
        long[] rx = new long[n];
        sweep(a, rc, rx, true);
        long res = Long.MAX_VALUE;
        for (int p = 1; p < n - 1; ++p) {
            long need = Math.max(lx[p - 1], rx[p + 1]) + 1;
            long cur = lc[p - 1] + rc[p + 1] + Math.max(0, need - a[p]);
            res = Math.min(res, cur);
        }
        return res;
    }

    // cost[i] / last[i]: for the part from the start (or from the end if rev) up to i.
    private void sweep(int[] a, long[] cost, long[] last, boolean rev) {
        int n = a.length;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long c = 0;
        for (int k = 0; k < n; ++k) {
            int i = rev ? n - 1 - k : k;
            long b = a[i] - k;
            pq.add(b);
            if (pq.peek() > b) {
                c += pq.peek() - b;
                pq.poll();
                pq.add(b);
            }
            cost[i] = c;
            last[i] = pq.peek() + k;
        }
    }
}

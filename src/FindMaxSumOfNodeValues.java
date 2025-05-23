import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMaxSumOfNodeValues {
    // greedy....we can always change even numbers of node in a tree
    public long maximumValueSum(int[] a, int k, int[][] edges) {
        int n = a.length;

        long minsac = (long) 1e9;
        long res = 0;
        int flips = 0;
        for (int i = 0; i < n; ++i) {
            long way1 = a[i];
            long way2 = a[i] ^ k;
            if (way1 > way2) {
                res += way1;
                minsac = Math.min(minsac, way1 - way2);
            } else {
                flips += 1;
                res += way2;
                minsac = Math.min(minsac, way2 - way1);
            }
        }
        if (flips % 2 == 0) {
            return res;
        } else {
            return res - minsac;
        }
    }
}


class FindMaxSumOfNodeValuesParityDp {
    private List<Integer>[] t;
    private long Max = (long) 1e9;
    private long Min = -Max;
    private long[][] dp;

    public long maximumValueSum(int[] a, int k, int[][] edges) {
        int n = a.length;
        t = new ArrayList[n];
        dp = new long[n][2];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }
        for (int[] e : edges) {
            t[e[0]].add(e[1]);
            t[e[1]].add(e[0]);
        }
        return solve(a, 0, -1, 0, k);
    }

    private long solve(int[] a, int i, int parent, int par, long k) {
        if (dp[i][par] != -1) return dp[i][par];

        long base = 0, sumPos = 0;
        long flips = 0, minpos = Max, maxneg = Min;

        for (int ne : t[i]) {
            if (ne == parent) continue;
            long w0 = solve(a, ne, i, 0, k);
            long w1 = solve(a, ne, i, 1, k);
            base += w0;
            long d = w1 - w0;
            if (d > 0) {
                sumPos += d;
                flips++;
                if (d < minpos) minpos = d;
            } else if (d < 0) {
                if (d > maxneg) maxneg = d;
            }
        }

        long total = base + sumPos;
        long alt1 = Min, alt2 = Min;
        if (minpos < Max) alt1 = total - minpos;
        if (maxneg > Min) alt2 = total + maxneg;
        long bestAlt = Math.max(alt1, alt2);

        long evenTotal = (flips % 2 == 0) ? total : bestAlt;
        long oddTotal = (flips % 2 == 1) ? total : bestAlt;

        long v0 = a[i], v1 = a[i] ^ k;
        dp[i][par] = (par == 0)
                ? Math.max(evenTotal + v0, oddTotal + v1)
                : Math.max(evenTotal + v1, oddTotal + v0);

        return dp[i][par];
    }
}

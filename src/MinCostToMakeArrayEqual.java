import java.util.*;

public class MinCostToMakeArrayEqual {
    // 2026-11-13 redo (one shot): sweep the sorted values, the target is always some a[i]
    // (cost is piecewise linear in X with breakpoints at a[i]).
    // Moving X from a[i-1] to a[i] by diff: everyone on the left pays +diff * (their cost sum),
    // everyone from i on saves diff * (their cost sum) -> only the DIFF matters, i.e. maintain the slope.
    public long minCost(int[] s, int[] t) {
        int n = s.length;
        long a[][] = new long[n][2];
        for (int i = 0; i < n; ++i) {
            a[i][0] = s[i];
            a[i][1] = t[i];
        }
        Arrays.sort(a, (x, y) -> Long.compare(x[0], y[0]));
        long[] cpref = new long[n];
        cpref[0] = a[0][1];
        for (int i = 1; i < n; ++i) {
            cpref[i] = cpref[i - 1] + a[i][1];
        }
        long all = cpref[n - 1];
        long rightcost = 0;
        for (int i = 1; i < n; ++i) {
            rightcost += (a[i][0] - a[0][0]) * a[i][1];
        }
        long leftcost = 0;
        long res = rightcost;
        for (int i = 1; i < n; ++i) {
            // use i as the number now
            long diff = a[i][0] - a[i - 1][0];
            long leftinc = cpref[i - 1] * diff;
            leftcost += leftinc;
            long rightdec = (all - cpref[i]) * diff + diff * a[i][1];
            rightcost -= rightdec;
            long ccost = leftcost + rightcost;
            res = Math.min(res, ccost);
        }
        return res;
    }

    public static void main(String[] args) {
        Random rd = new Random(3);
        int bad = 0;
        for (int it = 0; it < 3000; it++) {
            int n = 1 + rd.nextInt(7);
            int[] s = new int[n], t = new int[n];
            for (int i = 0; i < n; i++) { s[i] = 1 + rd.nextInt(10); t[i] = 1 + rd.nextInt(5); }
            long exp = Long.MAX_VALUE;
            for (int x = 0; x <= 11; x++) { long c = 0; for (int i = 0; i < n; i++) c += (long) Math.abs(s[i] - x) * t[i]; exp = Math.min(exp, c); }
            long got = new MinCostToMakeArrayEqual().minCost(s.clone(), t.clone());
            long got2 = new MinCostToMakeArrayEqualPrefixSum().minCost(s.clone(), t.clone());
            if (got != exp || got2 != exp) bad++;
        }
        System.out.println("bad=" + bad);
    }
}






class MinCostToMakeArrayEqualPrefixSum {
    // we scan from the first to the last and figure out the value for current one based on what we already have
    public long minCost(int[] ia, int[] ib) {
        int n = ia.length;
        long[][] a = new long[n][2];
        for (int i = 0; i < n; ++i) {
            a[i][0] = ia[i];
            a[i][1] = ib[i];
        }

        Arrays.sort(a, (x, y) -> Long.compare(x[0], y[0]));

        long afteraccu = 0;
        long aftercostsum = 0;
        for (int i = 0; i < n; ++i) {
            afteraccu += a[i][0] * a[i][1];
            aftercostsum += a[i][1];
        }

        long beforeaccu = 0;
        long beforecostsum = 0;
        long res = (long) 2e18;

        for (int i = 0; i < n; ++i) {
            afteraccu -= a[i][0] * a[i][1];
            aftercostsum -= a[i][1];

            long beforesum = a[i][0] * beforecostsum - beforeaccu;
            long aftersum = afteraccu - aftercostsum * a[i][0];

            long cur = beforesum + aftersum;
            res = Math.min(res, cur);

            beforeaccu += a[i][0] * a[i][1];
            beforecostsum += a[i][1];
        }

        return res;
    }
}

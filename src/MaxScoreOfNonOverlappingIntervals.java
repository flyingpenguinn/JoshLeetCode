import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxScoreOfNonOverlappingIntervals {
    private RV[][] dp;

    class Interval {
        int start;
        int end;
        long weight;
        int index;

        public Interval(int start, int end, long weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    public int[] maximumWeight(List<List<Integer>> input) {
        int n = input.size();
        Interval[] a = new Interval[n];
        for (int i = 0; i < n; ++i) {
            a[i] = new Interval(input.get(i).get(0), input.get(i).get(1), input.get(i).get(2), i);
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x.start, y.start));

        dp = new RV[n][5];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], null);
        }
        // start and max weight starting at i (might skip i)
        RV rt = solve(a, 0, 4);
        List<Integer> res = rt.indexes;
        int[] rr = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rr[i] = res.get(i);
        }
        Arrays.sort(rr);
        return rr;
    }


    class RV {
        List<Integer> indexes;
        long wsum;
        int ci;

        public RV(List<Integer> indexes, long wsum, int ci) {
            this.indexes = indexes;
            this.wsum = wsum;
            this.ci = ci;
        }
    }

    private RV solve(Interval[] a, int i, int rem) {
        int n = a.length;

        if (i == n) {
            return new RV(new ArrayList<>(), 0, n);
        }
        if (dp[i][rem] != null) {
            return dp[i][rem];
        }
        RV res = solve(a, i + 1, rem);
        RV way2 = new RV(new ArrayList<>(), 0, n);
        if (rem >= 1) {
            int cend = a[i].end;
            int pos = binary(a, cend);
            RV later = solve(a, pos, rem - 1);
            List<Integer> nl = new ArrayList<>();
            nl.addAll(later.indexes);
            nl.add(a[i].index);
            Collections.sort(nl);
            way2 = new RV(nl, later.wsum + a[i].weight, i);
        }
        if (way2.wsum > res.wsum) {
            res = way2;
        } else if (way2.wsum == res.wsum && better(way2, res)) {
            res = way2;
        }
        dp[i][rem] = res;
        return res;
    }

    private boolean better(RV v1, RV v2) {
        int i = 0;
        for (i = 0; i < v1.indexes.size() && i < v2.indexes.size(); ++i) {
            if (v1.indexes.get(i) < v2.indexes.get(i)) {
                return true;
            } else if (v1.indexes.get(i) > v2.indexes.get(i)) {
                return false;
            }
        }
        if (i == v1.indexes.size()) {
            return true;
        } else {
            return false;
        }
    }

    private int binary(Interval[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid].start > t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

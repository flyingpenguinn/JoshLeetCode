import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZeroArrayTransformationIv {
    public int minZeroArray(int[] a, int[][] qs) {
        boolean all0 = true;
        for (int ai : a) {
            if (ai != 0) {
                all0 = false;
                break;
            }
        }
        if (all0) {
            return 0;
        }
        int n = a.length;
        int qn = qs.length;
        int l = 1;
        int u = qn;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (doable(a, qs, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l > qn ? -1 : l;
    }

    private boolean doable(int[] a, int[][] qs, int k) {
        int n = a.length;
        List<Integer>[] ls = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            ls[i] = new ArrayList<>();
        }
        for (int i = 0; i < k; ++i) {
            int l = qs[i][0];
            int u = qs[i][1];
            int val = qs[i][2];
            for (int j = l; j <= u; ++j) {
                ls[j].add(val);
            }
        }
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            dp = new int[ls[i].size()][v + 1];
            for (int j = 0; j < dp.length; ++j) {
                Arrays.fill(dp[j], -1);
            }
            if (solve(ls[i], 0, v) != 1) {
                return false;
            }
        }
        return true;
    }

    private int solve(List<Integer> l, int i, int v) {
        int n = l.size();
        if (v < 0) {
            return 0;
        }
        if (i == n) {
            return v == 0 ? 1 : 0;
        }
        if (dp[i][v] != -1) {
            return dp[i][v];
        }
        int way1 = solve(l, i + 1, v - l.get(i));
        int way2 = solve(l, i + 1, v);
        int res = Math.max(way1, way2);
        dp[i][v] = res;
        return res;
    }

    private int[][] dp;
}

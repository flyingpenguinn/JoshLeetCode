import java.util.Arrays;

public class MaximizeConsecutiveElmentsInArrayAfterModification {
    // LC 3041, daily redo 2026-11-20 (~38 min, 1 WA).
    // Sort, then dp(i, plusone) = longest chain that starts with a[i] (+1 if plusone) and goes right.
    // The next element is the first a[j] >= current value; equal values that do not fit are skipped (the WA version could not skip them:
    // [2,4,5,4,4] -> 4 via 3,4,5,6 needs to skip one of the 4s).
    // Note: the skip loop is O(n) per state, so all-equal input is O(n^2) in the worst case (~2 s locally for n = 1e5).
    // The classic O(n log n) alternative is a value DP after sorting: dp[x + 1] = dp[x] + 1, then dp[x] = dp[x - 1] + 1 (this order).
    public int maxSelectedElements(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        dp = new Integer[n][2];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int way0 = solve(a, i, 0);
            int way1 = solve(a, i, 1);
            res = Math.max(res, Math.max(way0, way1));
        }
        return res;
    }

    private Integer[][] dp;

    private int solve(int[] a, int i, int plusone) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][plusone] != null) {
            return dp[i][plusone];
        }
        int res = 0;
        int cv = a[i] + plusone;
        int j = i + 1;
        while (j < n && a[j] < cv) {
            ++j;
        }
        if (j == n) {
            res = 1;
        } else {
            if (a[j] == cv + 1) {
                res = Math.max(res, 1 + solve(a, j, 0));
            } else if (a[j] == cv) {
                res = Math.max(res, 1 + solve(a, j, 1));
            } else {
                res = 1;
            }
        }
        dp[i][plusone] = res;
        return res;
    }
}

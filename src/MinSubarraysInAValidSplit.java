public class MinSubarraysInAValidSplit {
    private Integer[] dp;
    private int Max = (int) 1e9;

    public int validSubarraySplit(int[] a) {
        dp = new Integer[a.length];
        int rt = solve(a, 0);
        return rt >= Max ? -1 : rt;
    }

    private int solve(int[] a, int i) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int gcd = a[i];
        int res = Max;
        for (int j = i; j < n; ++j) {
            int ngcd = getGcd(gcd, a[j]);
            if (ngcd > 1) {
                int cur = 1 + solve(a, j + 1);
                res = Math.min(res, cur);
            }
        }
        dp[i] = res;
        return res;
    }

    private int getGcd(int a, int b) {
        if (a < b) {
            return getGcd(b, a);
        }
        return b == 0 ? a : getGcd(b, a % b);
    }
}

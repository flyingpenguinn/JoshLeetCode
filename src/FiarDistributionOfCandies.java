public class FiarDistributionOfCandies {
    private int Max = (int) 2e9;
    private Integer[][] dp;

    public int distributeCookies(int[] a, int k) {
        int n = a.length;
        dp = new Integer[(1 << n)][k + 1];
        return solve(a, ((1 << n) - 1), k);
    }

    private int solve(int[] a, int st, int ck) {
        int n = a.length;
        if (st == 0) {
            return 0;
        }
        if (ck == 0) {
            return Max;
        }
        if (dp[st][ck] != null) {
            return dp[st][ck];
        }
        int res = Max;
        for (int nst = st; nst > 0; nst = (nst - 1) & st) {
            int cur = count(nst, a, n);
            int next = st - nst;
            int later = solve(a, next, ck - 1);
            if (later >= Max) {
                continue;
            }
            int cost = Math.max(cur, later);
            res = Math.min(cost, res);
        }
        dp[st][ck] = res;
        return res;
    }

    private int count(int st, int[] a, int n) {
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (((st >> i) & 1) == 1) {
                res += a[i];
            }
        }
        return res;
    }
}

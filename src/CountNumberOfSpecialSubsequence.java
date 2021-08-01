public class CountNumberOfSpecialSubsequence {
    // st==0: must 0. if 0 go to 1
    // ==1: has 0, can 0 can 1.if 1 go to 2
    // ==2: has 1, can 1 can 2, if 2 to go 3
    // ==3: has 2, can be done. only takes 2
    private long mod = 1000000007;
    private Long[][] dp;

    public int countSpecialSubsequences(int[] a) {
        dp = new Long[a.length][4];
        long rt = solve(a, 0, 0);
        return (int) rt;
    }

    long solve(int[] a, int i, int st) {
        int n = a.length;
        if (i == n) {
            return st == 3 ? 1 : 0;
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        long nopick = solve(a, i + 1, st);
        long pick = 0;
        if (st == 0) {
            if (a[i] == 0) {
                pick = solve(a, i + 1, 1);
            }
        }
        if (st == 1) {
            if (a[i] == 0) {
                pick = solve(a, i + 1, 1);
            } else if (a[i] == 1) {
                pick = solve(a, i + 1, 2);
            }
        }
        if (st == 2) {
            if (a[i] == 1) {
                pick = solve(a, i + 1, 2);
            } else if (a[i] == 2) {
                pick = solve(a, i + 1, 3);
            }
        }
        if (st == 3) {
            if (a[i] == 2) {
                pick = solve(a, i + 1, 3);
            }
        }
        long rt = pick + nopick;
        rt %= mod;
        dp[i][st] = rt;
        return rt;
    }
}
